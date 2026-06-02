package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.v1_21_1_MetaData
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_10.data.packets.v1_21_10_MetaData
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_9.data.packets.v1_21_9_MetaData
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import org.bukkit.craftbukkit.entity.CraftEntity
import org.bukkit.entity.Entity

fun ClientPacketFactory.setEntityData(
    entity: Entity,
    /**
     * Read more about [Entity_Metadata_Format](https://minecraft.wiki/w/Java_Edition_protocol/Entity_metadata#Entity_Metadata_Format)
     */
    entityData: MutableList<EntityMetadata<*>>,
    callback: (packet: Shard_Packet<ClientboundSetEntityDataPacketData>) -> Unit
): Shard_Packet<ClientboundSetEntityDataPacketData> {

    val shardPacket = Shard_Packet<ClientboundSetEntityDataPacketData>()

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            val data = ClientboundSetEntityDataPacketData(
                (entity as CraftEntity).handle, entityData.map { entity.v1_21_1_MetaData(it) }.toMutableList()
            )
            shardPacket.packetData = data
            ClientPacketBuilder.setEntityDataPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            val data = ClientboundSetEntityDataPacketData(
                (entity as CraftEntity).handle, entityData.map { entity.v1_21_10_MetaData(it) }.toMutableList()
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.ClientPacketBuilder.setEntityDataPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            val data = ClientboundSetEntityDataPacketData(
                (entity as CraftEntity).handle, entityData.map { entity.v1_21_9_MetaData(it) }.toMutableList()
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.ClientPacketBuilder.setEntityDataPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            val data = ClientboundSetEntityDataPacketData(
                (entity as CraftEntity).handle, entityData.map { entity.v1_21_1_MetaData(it) }.toMutableList()
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.ClientPacketBuilder.setEntityDataPacket(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }
    shardPacket.packetObject = packet

    callback(shardPacket)
    return shardPacket
}