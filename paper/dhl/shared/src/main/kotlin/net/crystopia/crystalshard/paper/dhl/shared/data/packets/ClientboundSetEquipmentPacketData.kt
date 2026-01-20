package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.EquipmentSlot

data class ClientboundSetEquipmentPacketData(
    var entityId: Int,
    var equipmentList: MutableList<Pair<EquipmentSlot, org.bukkit.inventory.ItemStack>>
) 