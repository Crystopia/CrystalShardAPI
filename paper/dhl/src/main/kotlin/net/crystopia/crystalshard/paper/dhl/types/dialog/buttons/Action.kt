package net.crystopia.crystalshard.paper.dhl.types.dialog.buttons

import net.crystopia.crystalshard.dhl.shared.enums.dialog.ActionType
import org.bukkit.NamespacedKey

abstract class Action<T : Any>

data class ActionCustomAll(
    var id: NamespacedKey,
    var additionsNBT: MutableMap<String, String>
) : Action<ActionCustomAll>()

data class ActionStaticAction<T: Any>(
    var type: ActionType,
    var value: T,
) : Action<ActionStaticAction<*>>()

fun Action<*>.toDhl(): net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.Action<*> {
    return when (this) {
        is ActionCustomAll -> {
            net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionCustomAll(
                id = net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(
                    this.id.namespace,
                    this.id.key
                ), additionsNBT = this.additionsNBT
            )
        }

        is ActionStaticAction<*> -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionStaticAction(
            type = this.type,
            value = this.value
        )

        else -> throw IllegalArgumentException("Unknown action type")
    }
}