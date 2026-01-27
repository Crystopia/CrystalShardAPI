package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundUpdateMobEffectPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.entities.build
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket

class Shard_ClientboundUpdateMobEffectPacket : IPacket<ClientboundUpdateMobEffectPacketData> {

    override fun createPacket(
        packetObj: ClientboundUpdateMobEffectPacketData
    ): ClientboundUpdateMobEffectPacket {
        return ClientboundUpdateMobEffectPacket(
            packetObj.entityId,
            packetObj.effect.build(),
            packetObj.blend
        )
    }
}