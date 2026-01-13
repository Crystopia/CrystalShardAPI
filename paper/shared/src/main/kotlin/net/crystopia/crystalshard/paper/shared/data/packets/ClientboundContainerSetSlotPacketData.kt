package net.crystopia.crystalshard.paper.shared.data.packets

import org.bukkit.inventory.ItemStack

data class ClientboundContainerSetSlotPacketData(
    var id: Int,
    var revision: Int,
    var slot: Int,
    var item: ItemStack
)
