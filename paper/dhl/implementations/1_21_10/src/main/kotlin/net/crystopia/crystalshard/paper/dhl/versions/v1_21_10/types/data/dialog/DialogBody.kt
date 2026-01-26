package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.dialog

import io.papermc.paper.adventure.PaperAdventure
import net.kyori.adventure.text.Component
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack
import java.util.*

abstract class DialogBody<T : Any> {
    open fun build(): net.minecraft.server.dialog.body.DialogBody? {
        return null
    }
}

data class DialogBodyPlainMessage(
    var contents: Component,
    var width: Int
) : DialogBody<DialogBodyPlainMessage>() {
    override fun build(): net.minecraft.server.dialog.body.PlainMessage {
        return net.minecraft.server.dialog.body.PlainMessage(
            PaperAdventure.asVanilla(contents),
            width
        )
    }
}

data class DialogBodyItemBody(
    var item: ItemStack,
    var description: DialogBodyPlainMessage?,
    var showDecorations: Boolean,
    var showTooltip: Boolean,
    var width: Int,
    var height: Int
) : DialogBody<DialogBodyItemBody>() {
    override fun build(): net.minecraft.server.dialog.body.ItemBody {
        var mcDescription: net.minecraft.server.dialog.body.PlainMessage? = null
        if (description != null) mcDescription = description!!.build()

        return net.minecraft.server.dialog.body.ItemBody(
            CraftItemStack.asNMSCopy(item),
            Optional.ofNullable(mcDescription),
            showDecorations,
            showTooltip,
            width,
            height
        )
    }
}