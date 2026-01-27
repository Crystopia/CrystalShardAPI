package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import org.bukkit.inventory.ItemStack

data class ClientboundContainerSetSlotPacketData(
    var id: Int,
    var revision: Int,
    var slot: Int,
    var item: ItemStack
)
