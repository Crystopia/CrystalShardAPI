package net.crystopia.crystalshard.dhl.shared.data.packets.server

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.enums.packets.SelectAdvancementTabAction

data class SeenAdvancementsEvent(
    val action: SelectAdvancementTabAction,
    val tab: NamespacedKey
)
