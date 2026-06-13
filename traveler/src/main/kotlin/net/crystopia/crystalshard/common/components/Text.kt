package net.crystopia.crystalshard.common.components

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder

fun Component.line(line: Component): Component {
    return this.append(Component.text("\n").append(line))
}

fun ComponentBuilder<*, *>.line(line: ComponentBuilder<*, *>): ComponentBuilder<*, *> {
    return this.append(Component.text("\n").append(line))
}

fun Component.text(text: String): Component {
    return this.append(Component.text(text))
}

fun ComponentBuilder<*, *>.text(text: String): ComponentBuilder<*, *> {
    return this.append(Component.text(text))
}

fun Component.minimessage(text: String): Component {
    return this.append(Component.text().text(text))
}

fun ComponentBuilder<*, *>.minimessage(text: String): ComponentBuilder<*, *> {
    return this.append(Component.text().text(text))
}

fun ComponentBuilder<*, *>.text(
    text: String, font: String? = null
): ComponentBuilder<*, *> {
    return append(
        MINI_MESSAGE.deserialize(text).font(font?.let { Key.key(it) })
    )
}


fun ComponentBuilder<*, *>.text(
    text: String, font: String? = null, callback: ComponentBuilder<*, *>.() -> Unit = {}
): ComponentBuilder<*, *> {
    val component =
        Component.text().append(MINI_MESSAGE.deserialize(text)).font(font?.let { Key.key(it) }).also { it.callback() }
            .build()

    return append(component)
}

fun Component.text(
    text: String, font: String? = null, callback: ComponentBuilder<*, *>.() -> Unit = {}
): Component {
    val component =
        Component.text().append(MINI_MESSAGE.deserialize(text)).font(font?.let { Key.key(it) }).also { it.callback() }
            .build()

    return append(component)
}

fun Component.font(
    font: String
): Component = font(Key.key(font))

fun ComponentBuilder<*, *>.font(
    font: String
): ComponentBuilder<*, *> = font(Key.key(font))
