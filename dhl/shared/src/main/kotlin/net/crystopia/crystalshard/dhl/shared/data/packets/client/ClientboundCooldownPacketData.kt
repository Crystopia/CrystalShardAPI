package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.world.item.ItemStack

data class ClientboundCooldownPacketData(
    var item: ItemStack,
    var duration: Int
)
