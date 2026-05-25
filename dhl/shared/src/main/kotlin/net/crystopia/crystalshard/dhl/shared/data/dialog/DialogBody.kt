package net.crystopia.crystalshard.dhl.shared.data.dialog

import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack

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