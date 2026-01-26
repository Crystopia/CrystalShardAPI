package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.scoreboard.DisplayData
import net.crystopia.crystalshard.paper.dhl.shared.data.scoreboard.FixedFormatData
import net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.DisplaySlot
import net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.NumberFormat
import net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.ScoreBoardMode
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.numbers.BlankFormat
import net.minecraft.network.chat.numbers.FixedFormat
import net.minecraft.network.chat.numbers.StyledFormat
import net.minecraft.world.scores.Objective
import net.minecraft.world.scores.Scoreboard

data class ClientboundSetDisplayObjectivePacketData(
    var mode: ScoreBoardMode,
    var displaySlot: DisplaySlot,
    var displayData: DisplayData<*>
) {
    fun build(): Objective {

        val data = when (displayData.numberFormat) {
            NumberFormat.FIXED -> {
                val scoreboard = PaperAdventure.asVanilla((displayData.format as FixedFormatData).text)
                FixedFormat(scoreboard)
            }

            NumberFormat.STYLED -> {
                StyledFormat(Style.EMPTY)
            }

            NumberFormat.BLANK -> {
                BlankFormat()
            }
        }

        return Objective(
            Scoreboard(),
            displayData.name,
            displayData.criteria.id,
            PaperAdventure.asVanilla(displayData.displayName),
            displayData.renderType.id,
            displayData.displayAutoUpdate,
            data
        )
    }
}
