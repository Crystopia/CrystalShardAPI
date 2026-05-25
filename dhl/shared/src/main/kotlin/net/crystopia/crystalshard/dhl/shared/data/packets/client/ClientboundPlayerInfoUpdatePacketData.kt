package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.enums.packets.InfoUpdateAction
import net.minecraft.server.level.ServerPlayer

data class ClientboundPlayerInfoUpdatePacketData(
    val serverPlayer: ServerPlayer,
    val actions: MutableList<InfoUpdateAction>
)
