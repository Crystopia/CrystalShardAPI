package net.crystopia.crystalshard.paper.dhl.types.dialog

import net.kyori.adventure.text.Component
import org.bukkit.inventory.ItemStack

abstract class DialogBody<T : Any>

data class DialogBodyPlainMessage(
    var contents: Component,
    var width: Int
) : DialogBody<DialogBodyPlainMessage>()

data class DialogBodyItemBody(
    var item: ItemStack,
    var description: DialogBodyPlainMessage?,
    var showDecorations: Boolean,
    var showTooltip: Boolean,
    var width: Int,
    var height: Int
) : DialogBody<DialogBodyItemBody>()