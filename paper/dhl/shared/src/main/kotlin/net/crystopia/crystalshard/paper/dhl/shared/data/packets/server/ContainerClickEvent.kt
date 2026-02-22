package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

import net.crystopia.crystalshard.paper.dhl.shared.data.gui.Slot
import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.ButtonType
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ClickType
import org.bukkit.inventory.ItemStack

data class ContainerClickEvent(
    var containerId: Int,
    var stateId: Int,
    var slotNum: Short,
    var buttonNum: ButtonType,
    var clickType: ClickType,
    var changedSlots: MutableList<Slot>,
    var carriedItem: ItemStack?,
)