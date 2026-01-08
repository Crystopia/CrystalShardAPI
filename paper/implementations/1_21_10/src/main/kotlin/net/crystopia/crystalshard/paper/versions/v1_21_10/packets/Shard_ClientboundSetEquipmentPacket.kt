package net.crystopia.crystalshard.paper.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundSetEquipmentPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
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