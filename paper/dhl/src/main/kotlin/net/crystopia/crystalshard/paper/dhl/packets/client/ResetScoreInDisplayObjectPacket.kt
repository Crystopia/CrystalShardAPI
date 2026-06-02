package net.crystopia.crystalshard.paper.dhl.packets.client

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetScorePacketData
import net.crystopia.crystalshard.dhl.shared.data.scoreboard.ScoreData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.BlankFormatData
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.FixedFormatData
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.StyledFormatData
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil

fun ClientPacketFactory.resetScoreInDisplayObject(
    score: net.crystopia.crystalshard.paper.dhl.types.scoreboard.ScoreData<*>,
    callback: (packet: Shard_Packet<ClientboundSetScorePacketData>) -> Unit
): Shard_Packet<ClientboundSetScorePacketData> {

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
                    throw Exception("Unknown format type")
                }
            }
        )
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.resetScoreInDisplayObject(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.ClientPacketBuilder.resetScoreInDisplayObject(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.ClientPacketBuilder.resetScoreInDisplayObject(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.ClientPacketBuilder.resetScoreInDisplayObject(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundSetScorePacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}