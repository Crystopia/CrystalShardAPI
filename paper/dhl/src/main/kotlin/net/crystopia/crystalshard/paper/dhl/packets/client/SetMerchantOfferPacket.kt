
package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.merchant.MerchantOffer
import net.crystopia.crystalshard.dhl.shared.data.merchant.MerchantOffers
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMerchantOffersPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.minecraft.world.item.trading.ItemCost
import org.bukkit.craftbukkit.inventory.CraftItemStack

fun ClientPacketFactory.setMerchantOffer(
    windowId: Int,
    merchantOffers: net.crystopia.crystalshard.paper.dhl.types.merchant.MerchantOffers,
    levelProgress: Int,
    experience: Int,
    leveled: Boolean,
    refreshable: Boolean,
    callback: (packet: Shard_Packet<ClientboundMerchantOffersPacketData>) -> Unit
): Shard_Packet<ClientboundMerchantOffersPacketData> {

    val data = ClientboundMerchantOffersPacketData(
        windowId, MerchantOffers(
            offers = merchantOffers.offers.map {
                MerchantOffer(
                    baseCost = ItemCost(
                        CraftItemStack.asNMSCopy(it.baseCost.itemStack).item,
                        it.baseCost.count,
                    ),
                    optionalCost = if (it.optionalCost != null) ItemCost(
                        CraftItemStack.asNMSCopy(it.optionalCost!!.itemStack).item,
                        it.optionalCost!!.count,
                    ) else null,
                    result = CraftItemStack.asNMSCopy(it.result),
                    uses = it.uses,
                    maxUses = it.maxUses,
                    experience = it.experience,
                    priceMultiplier = it.priceMultiplier,
                    demand = it.demand,
                    specialPriceDiff = it.specialPrice,
                    specialPrice = it.specialPrice,
                    ignoreDiscounts = it.ignoreDiscounts,
                    experienceReward = it.experienceReward,
                )
            }.toMutableList()
        ), levelProgress, experience, leveled, refreshable
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.setMerchantOffer(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.ClientPacketBuilder.setMerchantOffer(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.ClientPacketBuilder.setMerchantOffer(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.ClientPacketBuilder.setMerchantOffer(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundMerchantOffersPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}