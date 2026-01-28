package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundRemoveMobEffectPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.entities.EffectType
import net.minecraft.network.protocol.game.ClientboundRemoveMobEffectPacket

class Shard_ClientboundRemoveMobEffectPacket : IPacket<ClientboundRemoveMobEffectPacketData> {

    override fun createPacket(
        packetObj: ClientboundRemoveMobEffectPacketData
    ): ClientboundRemoveMobEffectPacket {
        return ClientboundRemoveMobEffectPacket(
            packetObj.entityId,
            EffectType.convert(packetObj.effect).id,
        )
    }
}