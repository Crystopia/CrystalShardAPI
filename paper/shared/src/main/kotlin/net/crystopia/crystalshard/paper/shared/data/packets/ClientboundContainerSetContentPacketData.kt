package net.crystopia.crystalshard.paper.shared.data.packets

import org.bukkit.inventory.ItemStack

data class ClientboundContainerSetContentPacketData(
    var id: Int,
    var stateId: Int,
    var items: MutableList<ItemStack>,
    var carriedItem: ItemStack
)
