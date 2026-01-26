package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.dialog.buttons

import net.minecraft.server.dialog.ActionButton
import java.util.*

data class ActionButton<T : Action<T>>(
    var button: CommonButtonData,
    var action: Action<T>
) {
    fun build(): ActionButton {
        return ActionButton(
            button.build(),
            Optional.of(
                action.build()!!
            )
        )
    }
}
