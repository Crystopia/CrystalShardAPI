package net.crystopia.crystalshard.extras.toasts

import net.crystopia.crystalshard.common.extension.MINI_MESSAGE
import net.crystopia.crystalshard.common.extension.font
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player


class Toast(val player: Player) {

    enum class ToastTypes(val char: Char) {
        ADVANCEMENT('\uF001'), DEFAULT('\uF002'), RECIPE('\uF003'), SYSTEM('\uF004'), TUTORIAL('\uF005')
    }

    companion object {
        fun ADVANCEMENT(
            text: String
        ): Component {
            return Component.text().append(DefaultToastData.OFFSET_ADVANCEMENT)
                .append(Component.text(ToastTypes.ADVANCEMENT.char)).append(
                    DefaultToastData.OFFSET_ADVANCEMENT_TEXT
                ).append(
                    MINI_MESSAGE.deserialize(text)
                ).build()
        }

        fun DEFAULT(
            text: String
        ): Component {
            return Component.text().append(DefaultToastData.OFFSET_DEFAULT)
                .append(Component.text(ToastTypes.DEFAULT.char)).append(
                    DefaultToastData.OFFSET_DEFAULT_TEXT
                ).append(
                    MINI_MESSAGE.deserialize(text)
                ).build()
        }

        fun RECIPE(
            text: String
        ): Component {
            return Component.text().append(DefaultToastData.OFFSET_RECIPE)
                .append(Component.text(ToastTypes.RECIPE.char)).append(
                    DefaultToastData.OFFSET_RECIPE_TEXT
                ).append(
                    MINI_MESSAGE.deserialize(text)
                ).build()
        }

        fun SYSTEM(
            text: String
        ): Component {
            return Component.text().append(DefaultToastData.OFFSET_SYSTEM)
                .append(Component.text(ToastTypes.SYSTEM.char)).append(
                    DefaultToastData.OFFSET_SYSTEM_TEXT
                ).append(
                    MINI_MESSAGE.deserialize(text)
                ).build()
        }

        fun TUTORIAL(
            text: String
        ): Component {
            return Component.text().append(DefaultToastData.OFFSET_TUTORIAL)
                .append(Component.text(ToastTypes.TUTORIAL.char)).append(
                    DefaultToastData.OFFSET_TUTORIAL_TEXT
                ).append(
                    MINI_MESSAGE.deserialize(text)
                ).build()
        }
    }

    var font = "crystalshard:toasts"
    var text: Component = Component.text("")
    var background: Component = Component.text("${DefaultToastData.OFFSET_DEFAULT}${ToastTypes.DEFAULT.char}")

    fun send() {
        player.sendActionBar(
            Component.text().append(
                background.font(net.kyori.adventure.key.Key.key(font))
            ).append(
                text.font(net.kyori.adventure.key.Key.key(font))
            ).font(font).build()
        )
    }
}