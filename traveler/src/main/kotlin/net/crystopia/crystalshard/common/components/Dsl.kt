package net.crystopia.crystalshard.common.components

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder

fun cmp(string: String): Component {
    return MINI_MESSAGE.deserialize(string)
}

fun cmp(component: Component.() -> Unit) {
    Component.text().build().apply(component)
}

fun component(component: Component.() -> Unit) {
    Component.text().build().apply(component)
}

fun componentBuilder(component: ComponentBuilder<*, *>.() -> Unit) {
    Component.text().apply(component)
}

fun cmpb(string: String): ComponentBuilder<*, *> {
    return Component.text().text(string)
}

fun cmpb(component: ComponentBuilder<*, *>.() -> Unit) {
    Component.text().apply(component)
}