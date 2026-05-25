package net.crystopia.crystalshard.dhl.shared.data.dialog.input

import net.minecraft.network.chat.Component

data class DialogSingleOptionInputEntry(
    var id: String,
    var display: Component?,
    var initial: Boolean
)