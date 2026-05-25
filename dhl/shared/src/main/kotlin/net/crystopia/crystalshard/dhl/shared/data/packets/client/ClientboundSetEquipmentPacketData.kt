package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.enums.gui.EquipmentSlot
import net.minecraft.world.item.ItemStack

data class ClientboundSetEquipmentPacketData(
    var entityId: Int,
    var equipmentList: MutableList<Pair<EquipmentSlot, ItemStack>>
) 