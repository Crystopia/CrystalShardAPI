package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.dialog

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.DialogBodyItemBody
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.DialogBodyPlainMessage
import net.minecraft.server.dialog.body.ItemBody
import net.minecraft.server.dialog.body.PlainMessage
import org.bukkit.craftbukkit.inventory.CraftItemStack
import java.util.Optional

internal fun DialogBodyPlainMessage.build(): PlainMessage {
    return PlainMessage(
        PaperAdventure.asVanilla(contents),
        width
    )
}

internal fun DialogBodyItemBody.build(): ItemBody {
    var mcDescription: PlainMessage? = null
    if (description != null) mcDescription = description!!.build()

    return ItemBody(
        CraftItemStack.asNMSCopy(item),
        Optional.ofNullable(mcDescription),
        showDecorations,
        showTooltip,
        width,
        height
    )
}