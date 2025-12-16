package net.crystopia.crystalshard.extra.npc

import com.charleskorn.kaml.Location
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.crystopia.crystalshard.extra.npc.config.NpcVisibility
import net.kyori.adventure.text.Component
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
    override var id: NamespacedKey,
    override val name: String?,
    override val mirrorSkin: Boolean,
    override val location: org.bukkit.Location,
    override val actions: EnumSet<ClientboundPlayerInfoUpdatePacket.Action>,
    override val glowing: Boolean,
    override val type: EntityType?,
    override val equipment: MutableMap<EquipmentSlot?, ItemStack?>?,
    override val onInteract: Consumer<Player?>?,
    override val turnToPlayer: Boolean,
    override val turnToPlayerDistance: Int,
    override val visibilityDistance: Int,
    override val visibility: NpcVisibility?,
    gameProfile: GameProfile,
    clientInformation: ClientInformation,
    level: ServerLevel,
    server: MinecraftServer,
) : INpc, ServerPlayer(server, level, gameProfile, clientInformation) {
    
    
    
}