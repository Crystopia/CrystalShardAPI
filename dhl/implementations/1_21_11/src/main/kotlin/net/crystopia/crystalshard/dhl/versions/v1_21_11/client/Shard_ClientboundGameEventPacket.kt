package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundGameEventPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.enums.game.GameEventType
import net.minecraft.network.protocol.game.ClientboundGameEventPacket

class Shard_ClientboundGameEventPacket : IClientPacket<ClientboundGameEventPacketData> {

    override fun createPacket(
        packetObj: ClientboundGameEventPacketData
    ): ClientboundGameEventPacket {
        return ClientboundGameEventPacket(
            GameEventType.convert(packetObj.type).id,
            packetObj.action
        )
    }
}