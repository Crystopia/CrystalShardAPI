package net.crystopia.crystalshard.paper.shared.data.packets

import com.mojang.authlib.GameProfile
import net.crystopia.crystalshard.paper.shared.enums.packets.InfoUpdateAction
import net.minecraft.server.level.ServerPlayer
import java.util.*

data class ClientboundPlayerInfoUpdatePacketData(
    val serverPlayer: ServerPlayer,
    val gameProfile: GameProfile,
    val actions: EnumSet<InfoUpdateAction>
)
