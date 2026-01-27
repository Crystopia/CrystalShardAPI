package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import org.bukkit.inventory.ItemStack

data class ClientboundContainerSetContentPacketData(
    var id: Int,
    var stateId: Int,
    var items: MutableList<ItemStack>,
    var carriedItem: ItemStack
)
