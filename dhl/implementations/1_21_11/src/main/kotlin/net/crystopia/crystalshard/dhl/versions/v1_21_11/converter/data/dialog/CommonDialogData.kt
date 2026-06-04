package net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.dialog

import net.crystopia.crystalshard.dhl.shared.data.dialog.CommonDialogData
import net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyItemBody
import net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogBooleanInput
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogNumberRangeInput
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInput
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInput
import net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.dialog.input.build
import net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.enums.dialog.DialogAction
import net.minecraft.network.chat.Component
import net.minecraft.server.dialog.Input
import net.minecraft.server.dialog.body.DialogBody
import java.util.*

internal fun CommonDialogData.build(): net.minecraft.server.dialog.CommonDialogData {
    var component: Component? = null
    if (externalTitle != null) component = externalTitle

    val inputsData = mutableListOf<Input>()
    inputs.forEach { input ->

        val inputData = when (input) {
            is DialogBooleanInput -> {input.build()}
            is DialogNumberRangeInput -> {input.build()}
            is DialogSingleOptionInput -> {input.build()}
            is DialogTextInput -> {input.build()}
            else -> throw IllegalArgumentException("Unsupported input type: ${input.javaClass.name}")
        }

        inputsData.add(Input(input.id,inputData )) }

    val bodyData = mutableListOf<DialogBody>()
    body.forEach { body ->
        when (body) {
            is DialogBodyPlainMessage -> bodyData.add(body.build())
            is DialogBodyItemBody -> bodyData.add(body.build())
        }
    }

    return net.minecraft.server.dialog.CommonDialogData(
        title,
        Optional.ofNullable(component),
        canCloseWithEscape,
        pause,
        DialogAction.convert(afterAction).base,
        bodyData,
        inputsData
    )
}