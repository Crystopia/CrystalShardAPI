package net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input

import net.kyori.adventure.text.Component

data class DialogSingleOptionInputEntry(
    var id: String,
    var display: Component?,
    var initial: Boolean
)