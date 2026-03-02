package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.dialog.buttons

import net.minecraft.server.dialog.ActionButton
import java.util.Optional

fun net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.ActionButton<*>.build(): net.minecraft.server.dialog.ActionButton {
    return ActionButton(
        button.build(),
        Optional.of(
            action.build()!!
        )
    )
}