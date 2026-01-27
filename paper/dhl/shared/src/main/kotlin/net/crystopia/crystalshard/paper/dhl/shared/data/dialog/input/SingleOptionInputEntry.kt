package net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input

import io.papermc.paper.adventure.PaperAdventure
import net.kyori.adventure.text.Component
import net.minecraft.server.dialog.input.SingleOptionInput
import java.util.*

data class SingleOptionInputEntry(
    var id: String,
    var display: Component?,
    var initial: Boolean
)