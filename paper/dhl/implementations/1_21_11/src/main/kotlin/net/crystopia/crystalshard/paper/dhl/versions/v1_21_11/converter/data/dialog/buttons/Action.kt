package net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.converter.data.dialog.buttons

import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.Action
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.ActionCustomAll
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.ActionStaticAction
import net.kyori.adventure.text.event.ClickEvent
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.Identifier
import java.net.URI
import kotlin.collections.component1
import kotlin.collections.component2

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

fun ActionStaticAction.build(): net.minecraft.server.dialog.action.StaticAction {
    return when (event.action()) {
        ClickEvent.Action.CHANGE_PAGE -> {
            val data = event.payload() as ClickEvent.Payload.Int
            net.minecraft.server.dialog.action.StaticAction(net.minecraft.network.chat.ClickEvent.ChangePage(data.integer()))
        }

        ClickEvent.Action.COPY_TO_CLIPBOARD -> {
            val data = event.payload() as ClickEvent.Payload.Text
            net.minecraft.server.dialog.action.StaticAction(
                net.minecraft.network.chat.ClickEvent.CopyToClipboard(
                    data.value()
                )
            )
        }

        ClickEvent.Action.SUGGEST_COMMAND -> {
            val data = event.payload() as ClickEvent.Payload.Text
            net.minecraft.server.dialog.action.StaticAction(
                net.minecraft.network.chat.ClickEvent.SuggestCommand(
                    data.value()
                )
            )
        }

        ClickEvent.Action.RUN_COMMAND -> {
            val data = event.payload() as ClickEvent.Payload.Text
            net.minecraft.server.dialog.action.StaticAction(net.minecraft.network.chat.ClickEvent.RunCommand(data.value()))
        }

        ClickEvent.Action.OPEN_URL -> {
            val data = event.payload() as ClickEvent.Payload.Text
            net.minecraft.server.dialog.action.StaticAction(net.minecraft.network.chat.ClickEvent.OpenUrl(URI(data.value())))
        }

        ClickEvent.Action.OPEN_FILE -> {
            val data = event.payload() as ClickEvent.Payload.Text
            net.minecraft.server.dialog.action.StaticAction(net.minecraft.network.chat.ClickEvent.OpenFile(data.value()))
        }

        else -> throw Exception("Dialog and Custom actions are not implemented by StaticAction from CrystalShard.")
    }

}