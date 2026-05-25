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