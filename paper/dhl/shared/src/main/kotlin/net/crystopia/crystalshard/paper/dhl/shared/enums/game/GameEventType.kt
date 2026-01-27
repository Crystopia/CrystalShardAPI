package net.crystopia.crystalshard.paper.dhl.shared.enums.game

import net.minecraft.network.protocol.game.ClientboundGameEventPacket

enum class GameEventType {
    NO_RESPAWN_BLOCK_AVAILABLE,
    START_RAINING,
    STOP_RAINING,
    CHANGE_GAME_MODE,
    WIN_GAME,
    DEMO_EVENT,
    ARROW_HIT_PLAYER,
    RAIN_LEVEL_CHANGE,
    THUNDER_LEVEL_CHANGE,
    PUFFER_FISH_STING,
    GUARDIAN_ELDER_EFFECT,
    IMMEDIATE_RESPAWN,
    LIMITED_CRAFTING,
    LEVEL_CHUNKS_LOAD_START,
}