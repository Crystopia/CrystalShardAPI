package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.scoreboard.FixedFormatData
import net.crystopia.crystalshard.paper.dhl.shared.data.scoreboard.ScoreData
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.numbers.BlankFormat
import net.minecraft.network.chat.numbers.FixedFormat
import net.minecraft.network.chat.numbers.NumberFormat
import net.minecraft.network.chat.numbers.StyledFormat

data class ClientboundSetScorePacketData(
    var score: ScoreData<*>
) {

    fun build(): NumberFormat {
        val data = when (score.numberFormat) {
            net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.NumberFormat.FIXED -> {
                val scoreboard = PaperAdventure.asVanilla((score.format as FixedFormatData).text)
                FixedFormat(scoreboard)
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.NumberFormat.STYLED -> {
                StyledFormat(Style.EMPTY)
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.NumberFormat.BLANK -> {
                BlankFormat()
            }
        }
        return data
    }
}
