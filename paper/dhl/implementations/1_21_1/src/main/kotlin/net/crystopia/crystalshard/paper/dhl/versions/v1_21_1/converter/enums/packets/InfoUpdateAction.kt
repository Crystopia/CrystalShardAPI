package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.packets

import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket

internal enum class InfoUpdateAction(val action: ClientboundPlayerInfoUpdatePacket.Action) {
    ADD_PLAYER(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER),
    INITIALIZE_CHAT(ClientboundPlayerInfoUpdatePacket.Action.INITIALIZE_CHAT),
    UPDATE_GAME_MODE(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_GAME_MODE),
    UPDATE_LISTED(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED),
    UPDATE_LATENCY(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LATENCY),
    UPDATE_DISPLAY_NAME(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME);

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction): InfoUpdateAction {
            return valueOf(type.name)
        }
    }

}