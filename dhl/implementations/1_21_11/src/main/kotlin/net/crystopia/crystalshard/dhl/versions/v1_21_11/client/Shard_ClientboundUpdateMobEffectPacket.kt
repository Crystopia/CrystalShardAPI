package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundUpdateMobEffectPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.entities.build
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket

class Shard_ClientboundUpdateMobEffectPacket : IClientPacket<ClientboundUpdateMobEffectPacketData> {

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