package net.crystopia.crystalshard.paper.core.toasts

import net.crystopia.crystalshard.common.extension.font
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder
import org.bukkit.entity.Player

fun Player.toast(text: String, type: Toast.ToastTypes, font: String? = null) {
    val component: ComponentBuilder<*, *> = Component.text()
    component.font(font ?: "crystalshard:toasts")

    when (type) {
        Toast.ToastTypes.ADVANCEMENT -> {
            component.append(
                Toast.ADVANCEMENT(text)
            )
        }

        Toast.ToastTypes.DEFAULT -> {
            component.append(
                Toast.DEFAULT(text)
            )
        }

        Toast.ToastTypes.TUTORIAL -> {
            component.append(
                Toast.TUTORIAL(text)
            )
        }

        Toast.ToastTypes.RECIPE -> {
            component.append(
                Toast.RECIPE(text)
            )
        }

        Toast.ToastTypes.SYSTEM -> {
            component.append(
                Toast.SYSTEM(text)
            )
        }
    }
    sendActionBar(component.build())
}

fun Player.toast(block: Toast.() -> Unit): Toast {
    val toast = Toast(this)
    toast.block()
    return toast
}
