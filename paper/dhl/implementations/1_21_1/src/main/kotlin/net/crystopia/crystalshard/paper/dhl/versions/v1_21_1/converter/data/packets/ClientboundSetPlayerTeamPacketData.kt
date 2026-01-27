package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetPlayerTeamPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.TeamAction
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.teams.build
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket

fun ClientboundSetPlayerTeamPacketData.build(): ClientboundSetPlayerTeamPacket {
    return when (action) {
        TeamAction.ADD -> {
            ClientboundSetPlayerTeamPacket.createAddOrModifyPacket(
                team.build(),true
            )
        }

        TeamAction.REMOVE -> {
            ClientboundSetPlayerTeamPacket.createRemovePacket(
                team.build(),
            )
        }

        TeamAction.UPDATE -> {
            ClientboundSetPlayerTeamPacket.createAddOrModifyPacket(
                team.build(),
                false
            )
        }
    }

}