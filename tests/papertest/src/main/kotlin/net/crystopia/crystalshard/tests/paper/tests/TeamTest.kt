package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.packets.client.sendTeam
import net.crystopia.crystalshard.paper.dhl.shared.data.teams.Team
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.CollisionRule
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.NameTagVisibility
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.TeamAction
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TeamTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {

            ClientPacketFactory.sendTeam(
                action = TeamAction.ADD,
                team = Team(
                    name = "testteam",
                    teamDisplayName = Component.text().text("<pink>TEAM MEMBER</pink>").build(),
                    friendlyFlags = mutableListOf(),
                    nameTagVisibility = NameTagVisibility.ALWAYS,
                    collisionRule = CollisionRule.ALWAYS,
                    teamColor = 'A',
                    teamPrefix = Component.text("DUMM "),
                    teamSuffix = Component.text(" AM DÜMMSTEN"),
                    members = mutableListOf((sender as Player).name)
                )
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
            ClientPacketFactory.sendTeam(
                action = TeamAction.ADD,
                team = Team(
                    name = "xdvsdfsdfsfd",
                    teamDisplayName = Component.text().text("<pink>TEAM MEMBER</pink>").build(),
                    friendlyFlags = mutableListOf(),
                    nameTagVisibility = NameTagVisibility.ALWAYS,
                    collisionRule = CollisionRule.NEVER,
                    teamColor = 'A',
                    teamPrefix = Component.text("NPC", NamedTextColor.RED),
                    teamSuffix = Component.text(""),
                    members = mutableListOf((sender as Player).name)
                )
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
        }
    }

}