package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.entities.EntityDataSerializerType
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.player.Player

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
                    ) as EntityMetadata<*>
                )
            )
        }
        return ClientboundSetEntityDataPacket(packetObj.entityId, list)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> buildMetaData(data: EntityMetadata<T>): SynchedEntityData.DataValue<*> {
        when (data.type) {
            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.DATA_PLAYER_MODE_CUSTOMISATION -> {
                println(data.type.name)
                val DATA_PLAYER_MODE_CUSTOMISATION = Player.DATA_PLAYER_MODE_CUSTOMISATION
                return SynchedEntityData.DataValue.create(DATA_PLAYER_MODE_CUSTOMISATION, (data.value as Byte))
            }
            else -> {
                val serializer =
                    EntityDataSerializerType.convert(
                        data.type
                    ).type as EntityDataSerializer<T>
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, data.value)
            }
        }
    }
}