package net.crystopia.crystalshard.paper.dhl.shared.data.dialog

import io.papermc.paper.adventure.PaperAdventure
import net.kyori.adventure.text.Component
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack
import java.util.*

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