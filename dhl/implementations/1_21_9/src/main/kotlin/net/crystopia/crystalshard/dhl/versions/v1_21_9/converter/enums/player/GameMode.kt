package net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.enums.player

import net.minecraft.world.level.GameType

enum class GameMode(val id: GameType) {
    SURVIVAL(GameType.SURVIVAL), CREATIVE(GameType.CREATIVE), ADVENTURE(GameType.ADVENTURE), SPECTATOR(GameType.SPECTATOR);

    companion object {
        fun convert(type: net.crystopia.crystalshard.dhl.shared.enums.player.GameMode): GameMode {
            return valueOf(
                type.name
            )
        }
    }

}