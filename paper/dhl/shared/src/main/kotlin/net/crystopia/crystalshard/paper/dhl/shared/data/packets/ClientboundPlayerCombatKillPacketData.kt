package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.kyori.adventure.text.Component

data class ClientboundPlayerCombatKillPacketData(
    var entityId : Int,
    var message: Component
)
