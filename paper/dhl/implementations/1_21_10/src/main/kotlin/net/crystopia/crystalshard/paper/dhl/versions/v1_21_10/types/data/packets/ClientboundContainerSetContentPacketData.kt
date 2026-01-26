package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import org.bukkit.inventory.ItemStack

data class ClientboundContainerSetContentPacketData(
    var id: Int,
    var stateId: Int,
    var items: MutableList<ItemStack>,
    var carriedItem: ItemStack
)
