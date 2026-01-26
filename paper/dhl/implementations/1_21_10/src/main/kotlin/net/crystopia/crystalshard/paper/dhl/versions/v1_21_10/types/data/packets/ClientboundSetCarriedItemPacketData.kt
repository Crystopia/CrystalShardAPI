package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import org.bukkit.inventory.ItemStack

data class ClientboundSetCarriedItemPacketData(
    var item: ItemStack,
    var slot : Int
)
