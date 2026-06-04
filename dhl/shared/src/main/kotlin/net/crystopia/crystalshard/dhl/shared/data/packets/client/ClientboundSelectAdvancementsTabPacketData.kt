package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey

data class ClientboundSelectAdvancementsTabPacketData(
    var tab: NamespacedKey
)