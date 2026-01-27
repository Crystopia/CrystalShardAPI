package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.player

import net.minecraft.world.level.GameType

enum class GameMode(val id: GameType) {
    SURVIVAL(GameType.SURVIVAL),
    CREATIVE(GameType.CREATIVE),
    ADVENTURE(GameType.ADVENTURE),
    SPECTATOR(GameType.SPECTATOR);

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.player.GameMode): GameMode {
            return GameMode.valueOf(
                type.name
            )
        }
    }

}