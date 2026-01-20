package net.crystopia.crystalshard.paper.dhl.shared.enums.player

import net.minecraft.world.level.GameType

enum class GameMode(val id: GameType) {
    SURVIVAL(GameType.SURVIVAL),
    CREATIVE(GameType.CREATIVE),
    ADVENTURE(GameType.ADVENTURE),
    SPECTATOR(GameType.SPECTATOR);
}