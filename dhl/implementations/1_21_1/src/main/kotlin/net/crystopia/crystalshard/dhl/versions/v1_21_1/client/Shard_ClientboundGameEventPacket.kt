package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundGameEventPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundGameEventPacket

class Shard_ClientboundGameEventPacket : IPacket<ClientboundGameEventPacketData> {

    override fun createPacket(
        packetObj: ClientboundGameEventPacketData
    ): ClientboundGameEventPacket {
        return ClientboundGameEventPacket(
            net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.enums.game.GameEventType.convert(packetObj.type).id,
            packetObj.action
        )
    }
}