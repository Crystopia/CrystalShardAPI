package net.crystopia.crystalshard.paper.simulacrum.npc

import com.mojang.authlib.GameProfile
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.paper.simulacrum.types.config.npc.NpcSkinData
import net.crystopia.crystalshard.paper.simulacrum.types.interfaces.npcs.INpc
import net.minecraft.world.entity.EquipmentSlot
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * Basic implementation of INpc for standard NPC functionality. 
 * Please note that due to a lack of general use cases, it is only available for storage purposes and not for all implementations.
 */
class Npc(
    override var id: NamespacedKey,
    override val name: String? = null,
    override var playerEntity: Player,
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
        ClientPacketFactory.addEntitiesPacket(
            entityId = playerEntity.entityId,
            entityUUID = playerEntity.uniqueId,
            location = location,
            entityType = EntityType.PLAYER,
            data = 0,
            yHeadRot = 0.0,
        ) { packet ->
            packet.send(mutableListOf(player))
        }
    }

    /**
     * Implemented fun to simple spawn the NPC on the Location in the selected world.
     */
    override fun spawnAll() {
        ClientPacketFactory.addEntitiesPacket(
            entityId = playerEntity.entityId,
            entityUUID = playerEntity.uniqueId,
            location = location,
            entityType = EntityType.PLAYER,
            data = 0,
            yHeadRot = 0.0,
        ) { packet ->
            packet.send(Bukkit.getServer().onlinePlayers.toMutableList())
        }
    }

    /**
     * Implemented fun to simple remove the NPC from the Player.
     */
    override fun remove(player: Player) {
        ClientPacketFactory.removeEntitiesPacket(
            mutableListOf(playerEntity.entityId)
        ) { packet ->
            packet.send(mutableListOf(player))
        }
        ClientPacketFactory.playerInfoRemovePacket(
            mutableListOf(playerEntity.uniqueId)
        ) { packet ->
            packet.send(mutableListOf(player))
        }
    }

    /**
     * Implemented fun to simple remove the NPC from all Players.
     */
    override fun removeAll() {
        ClientPacketFactory.removeEntitiesPacket(
            mutableListOf(playerEntity.entityId)
        ) { packet ->
            packet.send(Bukkit.getServer().onlinePlayers.toMutableList())
        }
        ClientPacketFactory.playerInfoRemovePacket(
            mutableListOf(playerEntity.uniqueId)
        ) { packet ->
            packet.send(Bukkit.getServer().onlinePlayers.toMutableList())
        }
    }
}