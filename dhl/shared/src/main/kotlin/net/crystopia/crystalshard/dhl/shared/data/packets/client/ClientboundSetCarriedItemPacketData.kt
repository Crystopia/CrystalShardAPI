package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.world.item.ItemStack

data class ClientboundSetCarriedItemPacketData(
    var item: ItemStack,
    var slot : Int
)
