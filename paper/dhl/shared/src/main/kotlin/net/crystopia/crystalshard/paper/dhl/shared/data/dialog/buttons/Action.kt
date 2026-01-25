package net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons

import net.kyori.adventure.text.event.ClickEvent
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceLocation
import org.bukkit.NamespacedKey
import java.net.URI

abstract class Action<T : Any> {
    open fun build(): net.minecraft.server.dialog.action.Action? {
        return null
    }
}

data class ActionCustomAll(
    var id: NamespacedKey,
    var additionsNBT: MutableMap<String, String>
) : Action<ActionCustomAll>() {
    override fun build(): net.minecraft.server.dialog.action.CustomAll {
        val nbt = CompoundTag()
        additionsNBT.forEach { (key, data) ->
            nbt.putString(key, data)
        }

        return net.minecraft.server.dialog.action.CustomAll(
            ResourceLocation.tryBuild(id.namespace, id.key)!!,
            java.util.Optional.of(nbt)
        )
    }
}

data class ActionStaticAction(
    var event: ClickEvent
) : Action<ActionStaticAction>() {
    override fun build(): net.minecraft.server.dialog.action.StaticAction {
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
}
