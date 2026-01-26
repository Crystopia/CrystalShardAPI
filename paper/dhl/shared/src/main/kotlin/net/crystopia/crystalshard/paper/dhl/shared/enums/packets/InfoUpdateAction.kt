package net.crystopia.crystalshard.paper.dhl.shared.enums.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundPlayerInfoUpdatePacketData
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket

enum class InfoUpdateAction {
    ADD_PLAYER,
    INITIALIZE_CHAT,
    UPDATE_GAME_MODE,
    UPDATE_LISTED,
    UPDATE_LATENCY,
    UPDATE_DISPLAY_NAME,
    UPDATE_HAT,
    UPDATE_LIST_ORDER,
}