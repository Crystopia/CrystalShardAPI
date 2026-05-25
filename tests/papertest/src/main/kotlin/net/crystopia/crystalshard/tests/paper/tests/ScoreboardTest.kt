package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.click
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.scoreboard.DisplayData
import net.crystopia.crystalshard.dhl.shared.data.scoreboard.FixedFormatData
import net.crystopia.crystalshard.dhl.shared.data.scoreboard.ScoreData
import net.crystopia.crystalshard.dhl.shared.enums.scoreboard.*
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.sendObjectiveUpdate
import net.crystopia.crystalshard.paper.dhl.packets.client.setDisplayObjective
import net.crystopia.crystalshard.paper.dhl.packets.client.setScoreInDisplayObject
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class ScoreboardTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {

    override fun command() {
        test {
            val displayData = net.crystopia.crystalshard.paper.dhl.types.scoreboard.DisplayData(
                name = "testy",
                displayName = Component.text().text("<rainbow>PACKET BOARD</rainbow>").build(),
                displayAutoUpdate = false,
                numberFormat = NumberFormat.FIXED,
                format = net.crystopia.crystalshard.paper.dhl.types.scoreboard.FixedFormatData(
                    text = Component.text("TEST")
                ),
                renderType = RenderType.INTEGER,
                criteria = ObjectiveCriteria.DUMMY
            )

            ClientPacketFactory.sendObjectiveUpdate(
                ScoreBoardMode.CREATE,
                DisplaySlot.SIDEBAR,
                displayData,
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }

            ClientPacketFactory.setScoreInDisplayObject(
                net.crystopia.crystalshard.paper.dhl.types.scoreboard.ScoreData(
                    displayId = displayData.name,
                    ownerName = UUID.randomUUID().toString(),
                    score = 2,
                    displayName = Component.text().text("<gray>Packet sent...</gray>").click {
                        this.sendMessage(Component.text("SECRET TEXT", NamedTextColor.GRAY))
                    }.build(),
                    numberFormat = NumberFormat.FIXED,
                    format = net.crystopia.crystalshard.paper.dhl.types.scoreboard.FixedFormatData(
                        text = Component.text(" ")
                    ),
                )
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }

            ClientPacketFactory.setScoreInDisplayObject(
                net.crystopia.crystalshard.paper.dhl.types.scoreboard.ScoreData(
                    displayId = displayData.name,
                    ownerName = UUID.randomUUID().toString(),
                    score = 1,
                    displayName = Component.text().text(" ").build(),
                    numberFormat = NumberFormat.FIXED,
                    format = net.crystopia.crystalshard.paper.dhl.types.scoreboard.FixedFormatData(
                        text = Component.text("")
                    ),
                )
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }

            ClientPacketFactory.setScoreInDisplayObject(
                net.crystopia.crystalshard.paper.dhl.types.scoreboard.ScoreData(
                    displayId = displayData.name,
                    ownerName = UUID.randomUUID().toString(),
                    score = 0,
                    displayName = Component.text().text("<green><b>PACKET RECEIVED!</b></green>").build(),
                    numberFormat = NumberFormat.FIXED,
                    format = net.crystopia.crystalshard.paper.dhl.types.scoreboard.FixedFormatData(
                        text = Component.text(" ")
                    ),
                )
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }


            ClientPacketFactory.setDisplayObjective(
                displaySlot = DisplaySlot.SIDEBAR,
                displayData
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
        }
    }

}