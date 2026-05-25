package net.crystopia.crystalshard.dhl.shared.data.packets.server

import net.crystopia.crystalshard.dhl.shared.data.gui.Slot
import net.crystopia.crystalshard.dhl.shared.enums.gui.ButtonType
import net.crystopia.crystalshard.dhl.shared.enums.server.ClickType
import net.minecraft.world.item.ItemStack

data class ContainerClickEvent(
    var containerId: Int,
    var stateId: Int,
    var slotNum: Short,
    var buttonNum: ButtonType,
    var clickType: ClickType,
    var changedSlots: MutableList<Slot>,
    var carriedItem: ItemStack?,
)