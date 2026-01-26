package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import org.bukkit.inventory.ItemStack

data class ClientboundContainerSetSlotPacketData(
    var id: Int,
    var revision: Int,
    var slot: Int,
    var item: ItemStack
)
