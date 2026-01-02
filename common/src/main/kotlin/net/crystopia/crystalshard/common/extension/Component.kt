package net.crystopia.crystalshard.common.extension

import io.papermc.paper.dialog.Dialog
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.BuildableComponent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.DataComponentValue
import net.kyori.adventure.text.minimessage.MiniMessage
import java.util.*

val MINI_MESSAGE: MiniMessage = MiniMessage.miniMessage()

/**
 * Appends MiniMessage text with optional font.
 */
fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.text(
    text: String, font: String? = null
): B {
    return append(
        MINI_MESSAGE.deserialize(text).font(font?.let { Key.key(it) })
    )
}

/**
 * Appends MiniMessage text with optional font.
 */
fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.text(
    text: String, font: String? = null, callback: ComponentBuilder<*, *>.() -> Unit = {}
): B {
    val component =
        Component.text().append(MINI_MESSAGE.deserialize(text)).font(font?.let { Key.key(it) }).also { it.callback() }
            .build()

    return append(component)
}

/**
 * Convenience overload for setting font via String.
 */
fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.font(
    font: String
): B = font(Key.key(font))


/**
 * Creates a callback to interact with the click.
 */
fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.click(
    callback: Audience.() -> Unit = {}
): B {
    return clickEvent(ClickEvent.callback { audience ->
        callback(audience)
    })
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.openUrl(
    callback: Audience.() -> Unit = {}
): B {
    return clickEvent(ClickEvent.callback { audience ->
        callback(audience)
    })
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.openFile(
    file: String
): B {
    return clickEvent(ClickEvent.openFile(file))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.openDialog(
    dialog: Dialog
): B {
    return clickEvent(ClickEvent.showDialog(dialog))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.openUrl(
    url: String
): B {
    return clickEvent(ClickEvent.openUrl(url))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.runCommand(
    command: String
): B {
    return clickEvent(ClickEvent.runCommand(command))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.suggestCommand(
    command: String
): B {
    return clickEvent(ClickEvent.suggestCommand(command))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.copyToClipboard(
    text: String
): B {
    return clickEvent(ClickEvent.copyToClipboard(text))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.itemTooltip(
    itemKey: String, count: Int, dataComponents: MutableMap<Key, DataComponentValue>? = null
): B {
    return hoverEvent(dataComponents?.let {
        net.kyori.adventure.text.event.HoverEvent.showItem(
            Key.key(itemKey), count, it
        )
    })
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.textTooltip(
    text: Component
): B {
    return hoverEvent(net.kyori.adventure.text.event.HoverEvent.showText(text))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.entityTooltip(
    type: String, uuid: UUID
): B {
    return hoverEvent(net.kyori.adventure.text.event.HoverEvent.showEntity(Key.key(type), uuid))
}