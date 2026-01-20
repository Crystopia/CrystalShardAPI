package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import org.bukkit.inventory.ItemStack

data class ClientboundSetCarriedItemPacketData(
    var item: ItemStack,
    var slot : Int
)
