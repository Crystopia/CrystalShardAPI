package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.EquipmentSlot
import org.bukkit.inventory.ItemStack

data class ClientboundSetEquipmentPacketData(
    var entityId: Int,
    var equipmentList: MutableList<Pair<EquipmentSlot, ItemStack>>
) 