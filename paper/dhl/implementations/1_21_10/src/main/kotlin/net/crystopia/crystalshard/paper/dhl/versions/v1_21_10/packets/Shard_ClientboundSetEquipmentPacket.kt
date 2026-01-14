package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetEquipmentPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
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