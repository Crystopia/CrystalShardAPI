package net.crystopia.crystalshard.dhl.shared.data.dialog.buttons

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.enums.dialog.ActionType
import net.minecraft.network.chat.ClickEvent
import net.minecraft.server.dialog.Dialog

abstract class Action<T : Any>

data class ActionCustomAll(
    var id: NamespacedKey,
    var additionsNBT: MutableMap<String, String>
) : Action<ActionCustomAll>()

data class ActionStaticAction<T: Any>(
    var type: ActionType,
    var value: T,
    ) : Action<ActionStaticAction<*>>()
