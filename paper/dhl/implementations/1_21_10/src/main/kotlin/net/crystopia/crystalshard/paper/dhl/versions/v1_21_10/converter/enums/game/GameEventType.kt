package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.game

import net.minecraft.network.protocol.game.ClientboundGameEventPacket

enum class GameEventType(val id : ClientboundGameEventPacket.Type) {
    NO_RESPAWN_BLOCK_AVAILABLE(ClientboundGameEventPacket.NO_RESPAWN_BLOCK_AVAILABLE),
    START_RAINING(ClientboundGameEventPacket.START_RAINING),
    STOP_RAINING(ClientboundGameEventPacket.STOP_RAINING),
    CHANGE_GAME_MODE(ClientboundGameEventPacket.CHANGE_GAME_MODE),
    WIN_GAME(ClientboundGameEventPacket.WIN_GAME),
    DEMO_EVENT(ClientboundGameEventPacket.DEMO_EVENT),
    ARROW_HIT_PLAYER(ClientboundGameEventPacket.PLAY_ARROW_HIT_SOUND),
    RAIN_LEVEL_CHANGE(ClientboundGameEventPacket.RAIN_LEVEL_CHANGE),
    THUNDER_LEVEL_CHANGE(ClientboundGameEventPacket.THUNDER_LEVEL_CHANGE),
    PUFFER_FISH_STING(ClientboundGameEventPacket.PUFFER_FISH_STING),
    GUARDIAN_ELDER_EFFECT(ClientboundGameEventPacket.GUARDIAN_ELDER_EFFECT),
    IMMEDIATE_RESPAWN(ClientboundGameEventPacket.IMMEDIATE_RESPAWN),
    LIMITED_CRAFTING(ClientboundGameEventPacket.LIMITED_CRAFTING),
    LEVEL_CHUNKS_LOAD_START(ClientboundGameEventPacket.LEVEL_CHUNKS_LOAD_START);

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.game.GameEventType): GameEventType {
            return valueOf(
                type.name
            )
        }
    }
}