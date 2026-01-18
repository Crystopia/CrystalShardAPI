package net.crystopia.crystalshard.paper.dhl.shared.enums.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundPlayerInfoUpdatePacketData
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket

enum class InfoUpdateAction(val action: ClientboundPlayerInfoUpdatePacket.Action) {
    ADD_PLAYER(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER),
    INITIALIZE_CHAT(ClientboundPlayerInfoUpdatePacket.Action.INITIALIZE_CHAT),
    UPDATE_GAME_MODE(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_GAME_MODE),
    UPDATE_LISTED(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED),
    UPDATE_LATENCY(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LATENCY),
    UPDATE_DISPLAY_NAME(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME),
    UPDATE_HAT(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_HAT),
    UPDATE_LIST_ORDER(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LIST_ORDER),
}