package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.world.item.ItemStack

data class ClientboundContainerSetContentPacketData(
    var id: Int,
    var stateId: Int,
    var items: MutableMap<Int, ItemStack>,
    var carriedItem: ItemStack?
)
