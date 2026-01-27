package net.crystopia.crystalshard.paper.dhl.shared.data.packets

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
)
