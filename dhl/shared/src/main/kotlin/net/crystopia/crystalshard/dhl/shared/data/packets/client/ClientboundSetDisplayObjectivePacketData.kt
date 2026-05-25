package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.scoreboard.DisplayData
import net.crystopia.crystalshard.dhl.shared.enums.scoreboard.DisplaySlot
import net.crystopia.crystalshard.dhl.shared.enums.scoreboard.ScoreBoardMode

data class ClientboundSetDisplayObjectivePacketData(
    var mode: ScoreBoardMode,
    var displaySlot: DisplaySlot,
    var displayData: DisplayData<*>
)
