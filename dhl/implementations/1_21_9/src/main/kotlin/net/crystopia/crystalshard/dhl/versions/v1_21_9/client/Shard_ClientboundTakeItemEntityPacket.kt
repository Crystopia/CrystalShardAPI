package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTakeItemEntityPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket

class Shard_ClientboundTakeItemEntityPacket : IClientPacket<ClientboundTakeItemEntityPacketData> {

    override fun createPacket(
        packetObj: ClientboundTakeItemEntityPacketData
    ): ClientboundTakeItemEntityPacket {
        return ClientboundTakeItemEntityPacket(
            packetObj.itemId,
            packetObj.player.id,
            packetObj.amount,
        )
    }
}