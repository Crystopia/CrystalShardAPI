package net.crystopia.crystalshard.paper.dhl.packets.client

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetPlayerTeamPacketData
import net.crystopia.crystalshard.dhl.shared.data.teams.Team
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.enums.teams.TeamAction
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder

fun ClientPacketFactory.sendTeam(
    action: TeamAction,
    team: net.crystopia.crystalshard.paper.dhl.types.teams.Team,
    callback: (packet: ClientPacket<ClientboundSetPlayerTeamPacketData>) -> Unit
): ClientPacket<ClientboundSetPlayerTeamPacketData> {

    val data = ClientboundSetPlayerTeamPacketData(
        action, Team(
            name = team.name,
            teamDisplayName = PaperAdventure.asVanilla(team.teamDisplayName),
            friendlyFlags = team.friendlyFlags,
            nameTagVisibility = team.nameTagVisibility,
            collisionRule = team.collisionRule,
            teamColor = team.teamColor,
            teamPrefix = PaperAdventure.asVanilla(team.teamPrefix),
            teamSuffix = PaperAdventure.asVanilla(team.teamSuffix),
            members = team.members,
        )
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.sendTeam(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.ClientPacketBuilder.sendTeam(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.ClientPacketBuilder.sendTeam(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.ClientPacketBuilder.sendTeam(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundSetPlayerTeamPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}