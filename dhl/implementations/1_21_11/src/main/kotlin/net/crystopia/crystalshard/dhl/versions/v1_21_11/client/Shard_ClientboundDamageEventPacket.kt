package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundDamageEventPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundDamageEventPacket

class Shard_ClientboundDamageEventPacket : IPacket<ClientboundDamageEventPacketData> {

    override fun createPacket(
        packetObj: ClientboundDamageEventPacketData
    ): ClientboundDamageEventPacket {
        return ClientboundDamageEventPacket(
            packetObj.entity, packetObj.damageSource
        )
    }
}