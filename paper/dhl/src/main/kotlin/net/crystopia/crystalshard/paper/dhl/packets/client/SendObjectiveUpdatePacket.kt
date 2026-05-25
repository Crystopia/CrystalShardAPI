package net.crystopia.crystalshard.paper.dhl.packets.client

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetDisplayObjectivePacketData
import net.crystopia.crystalshard.dhl.shared.data.scoreboard.DisplayData
import net.crystopia.crystalshard.dhl.shared.enums.scoreboard.DisplaySlot
import net.crystopia.crystalshard.dhl.shared.enums.scoreboard.ScoreBoardMode
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.BlankFormatData
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.FixedFormatData
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.StyledFormatData

fun ClientPacketFactory.sendObjectiveUpdate(
    mode: ScoreBoardMode,
    displaySlot: DisplaySlot,
    displayData: net.crystopia.crystalshard.paper.dhl.types.scoreboard.DisplayData<*>,
    callback: (packet: Shard_Packet<ClientboundSetDisplayObjectivePacketData>) -> Unit
): Shard_Packet<ClientboundSetDisplayObjectivePacketData> {

    val data = ClientboundSetDisplayObjectivePacketData(
        mode, displaySlot, DisplayData(
            name = displayData.name,
            displayName = PaperAdventure.asVanilla(displayData.displayName),
            displayAutoUpdate = displayData.displayAutoUpdate,
            numberFormat = displayData.numberFormat,
            format = when (displayData.format) {
                is FixedFormatData -> {
                    val format = displayData.format as FixedFormatData
                    net.crystopia.crystalshard.dhl.shared.data.scoreboard.FixedFormatData(
                        PaperAdventure.asVanilla(format.text)
                    )
                }

                is StyledFormatData -> {
                    val format = displayData.format as StyledFormatData
                    net.crystopia.crystalshard.dhl.shared.data.scoreboard.StyledFormatData(format.style)
                }

                is BlankFormatData -> {
                    net.crystopia.crystalshard.dhl.shared.data.scoreboard.BlankFormatData()
                }

                else -> {
                    throw Exception("Unknown format type")
                }
            },
            renderType = displayData.renderType,
            criteria = displayData.criteria
        ),
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.sendObjectiveUpdate(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.PacketBuilder.sendObjectiveUpdate(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.PacketBuilder.sendObjectiveUpdate(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.sendObjectiveUpdate(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundSetDisplayObjectivePacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}