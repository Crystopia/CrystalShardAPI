package net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.packets

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetPlayerTeamPacketData
import net.crystopia.crystalshard.dhl.shared.enums.teams.TeamAction
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.teams.build
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