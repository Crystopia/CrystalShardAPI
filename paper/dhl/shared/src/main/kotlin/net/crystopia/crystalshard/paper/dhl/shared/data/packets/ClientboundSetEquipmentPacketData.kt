package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EquipmentSlot

data class ClientboundSetEquipmentPacketData(
    var entityId: Int,
    var equipmentList: MutableList<Pair<EquipmentSlot, org.bukkit.inventory.ItemStack>>
) 