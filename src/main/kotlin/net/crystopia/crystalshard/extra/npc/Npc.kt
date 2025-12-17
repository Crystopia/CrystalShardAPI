package net.crystopia.crystalshard.extra.npc

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.crystopia.crystalshard.extra.npc.config.NpcVisibility
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ClientInformation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EquipmentSlot
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*
import java.util.function.Consumer

class Npc(
    gameProfile: GameProfile,
    clientInformation: ClientInformation,
    level: ServerLevel,
    server: MinecraftServer,
    override var id: NamespacedKey,
    override val name: String? = null,
    override val mirrorSkin: Boolean = false,
    override val location: org.bukkit.Location,
    override val actions: EnumSet<ClientboundPlayerInfoUpdatePacket.Action?> = EnumSet.noneOf(
        ClientboundPlayerInfoUpdatePacket.Action::class.java
    ),
    override val glowing: Boolean = false,
    override val type: EntityType? = null,
    override val equipment: MutableMap<EquipmentSlot?, ItemStack?>? = mutableMapOf(),
    override val turnToPlayer: Boolean? = null,
    override val turnToPlayerDistance: Int? = null,
    override val visibilityDistance: Int? = null,
    override val visibility: NpcVisibility? = null,
    override var isTeamCreated: MutableMap<UUID?, Boolean?> = mutableMapOf(),
    override var isVisibleForPlayer: MutableMap<UUID?, Boolean?> = mutableMapOf(),
    override var isLookingAtPlayer: MutableMap<UUID?, Boolean?> = mutableMapOf(),
    override var lastPlayerInteraction: MutableMap<UUID?, Long?> = mutableMapOf(),
    override val permission: String? = null,
    override val skin: Property? = null,
    override var playerEntity: ServerPlayer,
    override val injectOnJoin: Boolean? = true, override var entityId: Int,
    override var onInteract: (player: Player) -> Unit? = {},
) : INpc, ServerPlayer(server, level, gameProfile, clientInformation) {
    override fun spawn(player: Player) {
        TODO("Not yet implemented")
    }

    override fun spawnAll() {
        TODO("Not yet implemented")
    }

    override fun remove(player: Player) {
        TODO("Not yet implemented")
    }

    override fun removeAll() {
        TODO("Not yet implemented")
    }


}