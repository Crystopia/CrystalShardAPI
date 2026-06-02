package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRemoveMobEffectPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.enums.entities.EffectType
import net.minecraft.network.protocol.game.ClientboundRemoveMobEffectPacket

class Shard_ClientboundRemoveMobEffectPacket : IClientPacket<ClientboundRemoveMobEffectPacketData> {

    override fun createPacket(
        packetObj: ClientboundRemoveMobEffectPacketData
    ): ClientboundRemoveMobEffectPacket {
        return ClientboundRemoveMobEffectPacket(
            packetObj.entityId,
            EffectType.convert(packetObj.effect).id
        )
    }
}