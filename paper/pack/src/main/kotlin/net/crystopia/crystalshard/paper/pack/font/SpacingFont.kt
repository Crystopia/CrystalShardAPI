package net.crystopia.crystalshard.paper.pack.font

import net.crystopia.crystalshard.common.extension.font
import net.kyori.adventure.text.BuildableComponent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder

/**
 * Uses font to move to the given row in the GUI.
 * > **This requires a resource pack**
 */
fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.toGuiRow(
    line: Int, customFont: String? = null, callback: ComponentBuilder<*, *>.() -> Unit
): B {
    var font = "crystalshard:line$line"

    if (customFont != null) font = customFont
    val component = Component.text().font(font)

    callback(component)
    return append(
        component
    )
}

/**
 * Use the Character to move X units in the direction.
 * > **This requires a resource pack**
 */
fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.go(
    units: Int, char: Char, callback: ComponentBuilder<*, *>.() -> Unit
): B {
    val component = Component.text().content(char.toString().repeat(units))
    callback(component)
    return append(
        component
    )
}


/**
 * Not implemented yet.
 * > **This requires a resource pack**
 */
fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.toCenter(): B {
    TODO("Not implemented yet.")
}

/**
 * Not implemented yet.
 * > **This requires a resource pack**
 */
fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.toLeft(): B {
    TODO("Not implemented yet.")
}
/**
 * Not implemented yet.
 * > **This requires a resource pack**
 */
fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.toRight(): B {
    TODO("Not implemented yet.")
}