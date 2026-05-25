package net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.dialog

import net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyItemBody
import net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage
import net.minecraft.server.dialog.body.ItemBody
import net.minecraft.server.dialog.body.PlainMessage
import java.util.*

internal fun DialogBodyPlainMessage.build(): PlainMessage {
    return PlainMessage(
        contents,
        width
    )
}

internal fun DialogBodyItemBody.build(): ItemBody {
    var mcDescription: PlainMessage? = null
    if (description != null) mcDescription = description!!.build()

    return ItemBody(
        item,
        Optional.ofNullable(mcDescription),
        showDecorations,
        showTooltip,
        width,
        height
    )
}