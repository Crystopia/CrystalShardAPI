package net.crystopia.crystalshard.paper.dhl.packets.client

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetScorePacketData
import net.crystopia.crystalshard.dhl.shared.data.scoreboard.ScoreData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoDhlTypeToConvert
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.BlankFormatData
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.FixedFormatData
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.StyledFormatData

fun ClientPacketFactory.setScoreInDisplayObject(
    score: net.crystopia.crystalshard.paper.dhl.types.scoreboard.ScoreData<*>,
    callback: (packet: ClientPacket<ClientboundSetScorePacketData>) -> Unit
): ClientPacket<ClientboundSetScorePacketData> {

    val data = ClientboundSetScorePacketData(
        ScoreData(
            displayId = score.displayId,
            ownerName = score.ownerName,
            score = score.score,
            displayName = PaperAdventure.asVanilla(score.displayName),
            numberFormat = score.numberFormat,
            format = when (score.format) {
                is FixedFormatData -> {
                    val format = score.format as FixedFormatData
                    net.crystopia.crystalshard.dhl.shared.data.scoreboard.FixedFormatData(
                        PaperAdventure.asVanilla(format.text)
                    )
                }

                is StyledFormatData -> {
                    val format = score.format as StyledFormatData
                    net.crystopia.crystalshard.dhl.shared.data.scoreboard.StyledFormatData(format.style)
                }

                is BlankFormatData -> {
                    net.crystopia.crystalshard.dhl.shared.data.scoreboard.BlankFormatData()
                }

                else -> {
                    throw NoDhlTypeToConvert("No type found")
                }
            }
        )
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.setScoreInDisplayObject(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.setScoreInDisplayObject(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.setScoreInDisplayObject(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.setScoreInDisplayObject(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundSetScorePacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}