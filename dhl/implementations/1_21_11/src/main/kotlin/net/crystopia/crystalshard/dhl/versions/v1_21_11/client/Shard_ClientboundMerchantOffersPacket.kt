package net.crystopia.crystalshard.dhl.versions.v1_21_11.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMerchantOffersPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.merchant.build
import net.minecraft.network.protocol.game.ClientboundMerchantOffersPacket

class Shard_ClientboundMerchantOffersPacket : IClientPacket<ClientboundMerchantOffersPacketData> {
    override fun createPacket(packetObj: ClientboundMerchantOffersPacketData): ClientboundMerchantOffersPacket {
        return ClientboundMerchantOffersPacket(
            packetObj.windowId,
            packetObj.merchantOffers.build(),
            packetObj.levelProgress,
            packetObj.experience,
            packetObj.leveled,
            packetObj.refreshable
        )
    }
}