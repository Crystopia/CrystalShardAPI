package net.crystopia.crystalshard.extras.npc

import com.mojang.authlib.GameProfile
import net.crystopia.crystalshard.shared.config.npc.NpcSkinData
import net.crystopia.crystalshard.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.shared.interfaces.npcs.INpc
import net.crystopia.crystalshard.extras.factories.PacketFactory
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.phys.Vec3
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.EnumSet
import java.util.UUID

/**
 * Basic implementation of INpc for standard NPC functionality. 
 * Please note that due to a lack of general use cases, it is only available for storage purposes and not for all implementations.
 */
class Npc(
    override var id: NamespacedKey,
    override val name: String? = null,
    override var playerEntity: ServerPlayer,
    override var gameProfile: GameProfile,
    override val mirrorSkin: Boolean = false,
    override val location: Location,
    override val actions: EnumSet<InfoUpdateAction> = EnumSet.noneOf(
        InfoUpdateAction::class.java
    ),
    override val glowing: Boolean = false,
    override val type: EntityType? = null,
    override val equipment: MutableMap<EquipmentSlot?, ItemStack?>? = mutableMapOf(),
    override val turnToPlayer: Boolean? = null,
    override val turnToPlayerDistance: Int? = null,
    override var isVisibleForPlayer: MutableMap<UUID?, Boolean?>? = mutableMapOf(),
    override val skin: NpcSkinData? = null,
    override var entityId: Int,
    override var isTeamCreated: MutableMap<UUID?, Boolean?>? = mutableMapOf(),
    override var isLookingAtPlayer: MutableMap<UUID?, Boolean?>? = mutableMapOf(),
    override val permission: String? = "npc.${id.namespace}.${id.key}",
    override val spawnOnJoin: Boolean? = null,
) : INpc {

    /**
     * Implemented fun to simple spawn the NPC on the Location in the selected world.
     * 
     * Default location is 
     * ```Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0, 0.0F, 0.0F)```
     */
    override fun spawn(player: Player) {
        PacketFactory.addEntitiesPacket(
            entityId = playerEntity.id,
            entityUUID = playerEntity.uuid,
            location = location,
            entityType = playerEntity.type,
            data = 0,
            deltaMovement = Vec3.ZERO,
            yHeadRot = 0.0,
        ) { packet ->
            PacketFactory.sendPacket(packet, mutableListOf(player))
        }
    }

    /**
     * Implemented fun to simple spawn the NPC on the Location in the selected world.
     */
    override fun spawnAll() {
        PacketFactory.addEntitiesPacket(
            entityId = playerEntity.id,
            entityUUID = playerEntity.uuid,
            location = location,
            entityType = playerEntity.type,
            data = 0,
            deltaMovement = Vec3.ZERO,
            yHeadRot = 0.0,
        ) { packet ->
            PacketFactory.sendPacket(packet, Bukkit.getServer().onlinePlayers.toMutableList())
        }
    }

    /**
     * Implemented fun to simple remove the NPC from the Player.
     */
    override fun remove(player: Player) {
        PacketFactory.removeEntitiesPacket(
            mutableListOf(playerEntity.id)
        ) { packet ->
            PacketFactory.sendPacket(packet, mutableListOf(player))
        }
        PacketFactory.playerInfoRemovePacket(
            mutableListOf(playerEntity.uuid)
        ) { packet ->
            PacketFactory.sendPacket(packet, mutableListOf(player))
        }
    }

    /**
     * Implemented fun to simple remove the NPC from all Players.
     */
    override fun removeAll() {
        PacketFactory.removeEntitiesPacket(
            mutableListOf(playerEntity.id)
        ) { packet ->
            PacketFactory.sendPacket(packet, Bukkit.getServer().onlinePlayers.toMutableList())
        }
        PacketFactory.playerInfoRemovePacket(
            mutableListOf(playerEntity.uuid)
        ) { packet ->
            PacketFactory.sendPacket(packet, Bukkit.getServer().onlinePlayers.toMutableList())
        }
    }
}