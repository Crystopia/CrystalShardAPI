package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundGameEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.game.GameEventType
import net.minecraft.network.protocol.game.ClientboundGameEventPacket

class Shard_ClientboundGameEventPacket : IPacket<ClientboundGameEventPacketData> {

    override fun createPacket(
        packetObj: ClientboundGameEventPacketData
    ): ClientboundGameEventPacket {
        return ClientboundGameEventPacket(
            GameEventType.convert(packetObj.type).id,
            packetObj.action
        )
    }
}