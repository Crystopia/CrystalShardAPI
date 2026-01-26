package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.MerchantOffers

data class ClientboundMerchantOffersPacketData(
    var windowId: Int,
    var merchantOffers: MerchantOffers,
    var levelProgress: Int,
    var experience: Int,
    var leveled: Boolean,
    var refreshable: Boolean
)
