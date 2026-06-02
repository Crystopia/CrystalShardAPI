package net.crystopia.crystalshard.paper.dhl.types.dialog

import io.papermc.paper.adventure.PaperAdventure
import net.kyori.adventure.text.Component
import org.bukkit.craftbukkit.inventory.CraftItemStack
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

fun DialogBody<*>.toDhl(): net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBody<*> {
    return when (this) {
        is DialogBodyPlainMessage -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
            contents = PaperAdventure.asVanilla(this.contents),
            width = this.width
        )

        is DialogBodyItemBody -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyItemBody(
            item = CraftItemStack.asNMSCopy(this.item),
            description = if (this.description != null) net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
                contents = PaperAdventure.asVanilla(this.description!!.contents),
                width = this.description?.width ?: 0
            ) else null,
            showDecorations = this.showDecorations,
            showTooltip = this.showTooltip,
            width = this.width,
            height = this.height
        )

        else -> throw IllegalArgumentException("Unknown body type ${this.javaClass}")
    }
}