package net.crystopia.crystalshard.paper.core.extension

import io.papermc.paper.dialog.Dialog
import net.kyori.adventure.text.BuildableComponent
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.event.ClickEvent

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.openDialog(
    dialog: Dialog
): B {
    return clickEvent(ClickEvent.showDialog(dialog))
}