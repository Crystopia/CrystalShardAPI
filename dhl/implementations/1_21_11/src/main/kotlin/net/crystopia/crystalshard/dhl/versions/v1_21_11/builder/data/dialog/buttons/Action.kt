package net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.dialog.buttons

import net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionCustomAll
import net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionStaticAction
import net.crystopia.crystalshard.dhl.shared.enums.dialog.ActionType
import net.minecraft.core.Holder
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.ClickEvent
import net.minecraft.resources.Identifier
import net.minecraft.server.dialog.Dialog
import java.net.URI

fun ActionCustomAll.build(): net.minecraft.server.dialog.action.CustomAll {
    val nbt = CompoundTag()
    additionsNBT.forEach { (key, data) ->
        nbt.putString(key, data)
    }

    return net.minecraft.server.dialog.action.CustomAll(
        Identifier.tryBuild(id.namespace, id.key)!!,
        java.util.Optional.of(nbt)
    )
}

fun ActionStaticAction<*>.build(): net.minecraft.server.dialog.action.StaticAction {
    val event = when (type) {
        ActionType.URL -> ClickEvent.OpenUrl(URI(value as String))
        ActionType.COPY -> ClickEvent.CopyToClipboard(value as String)
        ActionType.FILE -> ClickEvent.OpenFile(value as String)
        ActionType.BOOK_PAGE -> ClickEvent.ChangePage(value.toString().toInt())
        ActionType.DIALOG -> ClickEvent.ShowDialog(Holder.direct(value as Dialog))
        ActionType.SUGGEST_COMMAND -> ClickEvent.SuggestCommand(value as String)
        ActionType.RUN_COMMAND -> ClickEvent.RunCommand(value as String)
    }
    return net.minecraft.server.dialog.action.StaticAction(event)
}