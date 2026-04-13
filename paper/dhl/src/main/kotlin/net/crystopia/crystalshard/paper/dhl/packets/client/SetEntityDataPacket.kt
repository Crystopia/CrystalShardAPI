package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder

fun ClientPacketFactory.setEntityData(
    entityId: Int,
    /**
     * Read more about [Entity_Metadata_Format](https://minecraft.wiki/w/Java_Edition_protocol/Entity_metadata#Entity_Metadata_Format)
     */
    entityData: MutableList<EntityMetadata<*>>,
    callback: (packet: Shard_Packet<ClientboundSetEntityDataPacketData>) -> Unit
): Shard_Packet<ClientboundSetEntityDataPacketData> {

    val data = ClientboundSetEntityDataPacketData(
        entityId, entityData
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.setEntityDataPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.setEntityDataPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.setEntityDataPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setEntityDataPacket(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }


    val shardPacket = Shard_Packet<ClientboundSetEntityDataPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet

    callback(shardPacket)
    return shardPacket
}