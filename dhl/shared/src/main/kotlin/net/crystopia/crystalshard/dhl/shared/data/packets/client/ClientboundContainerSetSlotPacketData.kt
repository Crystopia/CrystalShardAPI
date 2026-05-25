package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.world.item.ItemStack

data class ClientboundContainerSetSlotPacketData(
    var id: Int,
    var revision: Int,
    var slot: Int,
    var item: ItemStack
)
