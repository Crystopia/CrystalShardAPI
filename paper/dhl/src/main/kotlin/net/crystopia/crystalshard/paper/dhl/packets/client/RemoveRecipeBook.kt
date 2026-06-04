package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipeBookRemovePacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil

fun ClientPacketFactory.removeRecipeBook(
    ids: MutableList<Int>,
    callback: (packet: ClientPacket<ClientboundRecipeBookRemovePacketData>) -> Unit
): ClientPacket<ClientboundRecipeBookRemovePacketData> {

    val shardPacket = ClientPacket<ClientboundRecipeBookRemovePacketData>()
    val data = ClientboundRecipeBookRemovePacketData(
        ids = ids,
    )
    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.removeRecipeBook(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.removeRecipeBook(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.removeRecipeBook(
                data
            )
        }

        ServerVersion.v1_21_1 -> {

            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.removeRecipeBook(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}