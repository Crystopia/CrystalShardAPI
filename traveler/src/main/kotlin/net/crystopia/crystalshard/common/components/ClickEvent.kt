package net.crystopia.crystalshard.common.components

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.event.ClickEvent

fun ComponentBuilder<*, *>.click(
    callback: Audience.() -> Unit = {}
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.callback { audience ->
        callback(audience)
    })
}

fun Component.click(
    callback: Audience.() -> Unit = {}
): Component {
    return clickEvent(ClickEvent.callback { audience ->
        callback(audience)
    })
}

fun ComponentBuilder<*, *>.openUrl(
    callback: Audience.() -> Unit = {}
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.callback { audience ->
        callback(audience)
    })
}

fun Component.openUrl(
    callback: Audience.() -> Unit = {}
): Component {
    return clickEvent(ClickEvent.callback { audience ->
        callback(audience)
    })
}

fun ComponentBuilder<*, *>.openFile(
    file: String
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.openFile(file))
}

fun Component.openFile(
    file: String
): Component {
    return clickEvent(ClickEvent.openFile(file))
}

fun ComponentBuilder<*, *>.openUrl(
    url: String
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.openUrl(url))
}

fun Component.openUrl(
    url: String
): Component {
    return clickEvent(ClickEvent.openUrl(url))
}

fun ComponentBuilder<*, *>.runCommand(
    command: String
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.runCommand(command))
}

fun Component.runCommand(
    command: String
): Component {
    return clickEvent(ClickEvent.runCommand(command))
}

fun ComponentBuilder<*, *>.suggestCommand(
    command: String
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.suggestCommand(command))
}

fun Component.suggestCommand(
    command: String
): Component {
    return clickEvent(ClickEvent.suggestCommand(command))
}

fun ComponentBuilder<*, *>.copyToClipboard(
    text: String
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.copyToClipboard(text))
}

fun Component.copyToClipboard(
    text: String
): Component {
    return clickEvent(ClickEvent.copyToClipboard(text))
}
