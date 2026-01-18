package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.network.syncher.SynchedEntityData

class Shard_ClientboundSetEntityDataPacket : IPacket<ClientboundSetEntityDataPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetEntityDataPacketData
    ): ClientboundSetEntityDataPacket {
        val list = mutableListOf<SynchedEntityData.DataValue<*>>()
        packetObj.entityData.forEach { (index, type, value) ->
            list.add(
                buildMetaData(
                    EntityMetadata(
                        index = index,
                        type = type,
                        value = value
                    )
                )
            )
        }
        return ClientboundSetEntityDataPacket(packetObj.entityId, list)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> buildMetaData(data: EntityMetadata<T>): SynchedEntityData.DataValue<*> {
        val serializer = data.type.type as EntityDataSerializer<T>
        val accessor = EntityDataAccessor(data.index, serializer)
        val dataValue = SynchedEntityData.DataValue.create(accessor, data.value)
        return dataValue
    }
}