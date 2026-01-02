package net.crystopia.crystalshard.versions.v1_21_1.packets


import net.crystopia.crystalshard.shared.data.packets.ClientboundSetEquipmentPacketData
import net.crystopia.crystalshard.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket

class Shard_ClientboundSetEquipmentPacket : IPacket<ClientboundSetEquipmentPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetEquipmentPacketData
    ): ClientboundSetEquipmentPacket {

        return ClientboundSetEquipmentPacket(
            packetObj.entityId, packetObj.equipmentList
        )

    }

}