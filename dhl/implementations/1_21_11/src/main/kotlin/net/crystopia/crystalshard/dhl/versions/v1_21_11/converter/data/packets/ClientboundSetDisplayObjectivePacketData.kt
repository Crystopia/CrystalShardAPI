package net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.packets

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetDisplayObjectivePacketData
import net.crystopia.crystalshard.dhl.shared.data.scoreboard.FixedFormatData
import net.crystopia.crystalshard.dhl.shared.enums.scoreboard.NumberFormat
import net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.enums.scoreboard.ObjectiveCriteria
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.numbers.BlankFormat
import net.minecraft.network.chat.numbers.FixedFormat
import net.minecraft.network.chat.numbers.StyledFormat
import net.minecraft.world.scores.Objective
import net.minecraft.world.scores.Scoreboard

fun ClientboundSetDisplayObjectivePacketData.build(): Objective {

    val data = when (displayData.numberFormat) {
        NumberFormat.FIXED -> {
            val scoreboard = (displayData.format as FixedFormatData).text
            FixedFormat(scoreboard)
        }

        NumberFormat.STYLED -> {
            StyledFormat(Style.EMPTY)
        }

        NumberFormat.BLANK -> {
            BlankFormat.INSTANCE
        }
    }

    return Objective(
        Scoreboard(),
        displayData.name,
        ObjectiveCriteria.convert(displayData.criteria).id,
        displayData.displayName,
        net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.enums.scoreboard.RenderType.convert(displayData.renderType).id,
        displayData.displayAutoUpdate,
        data
    )
}