package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.MerchantOffers
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMerchantOffersPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder

fun ClientPacketFactory.setMerchantOffer(
    windowId: Int,
    merchantOffers: MerchantOffers,
    levelProgress: Int,
    experience: Int,
    leveled: Boolean,
    refreshable: Boolean,
    callback: (packet: Shard_Packet<ClientboundMerchantOffersPacketData>) -> Unit
): Shard_Packet<ClientboundMerchantOffersPacketData> {

    val data = ClientboundMerchantOffersPacketData(
        windowId, merchantOffers, levelProgress, experience, leveled, refreshable
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.setMerchantOffer(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.setMerchantOffer(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.setMerchantOffer(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setMerchantOffer(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundMerchantOffersPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}