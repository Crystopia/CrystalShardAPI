package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundDamageEventPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundDamageEventPacket

class Shard_ClientboundDamageEventPacket : IClientPacket<ClientboundDamageEventPacketData> {

    override fun createPacket(
        packetObj: ClientboundDamageEventPacketData
    ): ClientboundDamageEventPacket {
        return ClientboundDamageEventPacket(
            packetObj.entity, (packetObj.damageSource)
        )
    }
}