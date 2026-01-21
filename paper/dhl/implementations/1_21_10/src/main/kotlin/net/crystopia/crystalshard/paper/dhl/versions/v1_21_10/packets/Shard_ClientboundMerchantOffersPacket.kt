package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundMerchantOffersPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundMerchantOffersPacket

class Shard_ClientboundMerchantOffersPacket : IPacket<ClientboundMerchantOffersPacketData> {
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