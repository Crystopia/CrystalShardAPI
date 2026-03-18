package net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.converter.data.dialog.buttons

import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.ActionCustomAll
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.ActionStaticAction
import net.minecraft.server.dialog.ActionButton
import java.util.*

fun net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.ActionButton<*>.build(): net.minecraft.server.dialog.ActionButton {
    val action = when (action) {
        is ActionCustomAll -> (action as ActionCustomAll).build()
        is ActionStaticAction -> (action as ActionStaticAction).build()
        else -> throw NoSuchMethodException("Use ActionStaticAction or ActionCustomAll as type.")
    }


    return ActionButton(
        button.build(),
        Optional.of(
            action
        )
    )
}