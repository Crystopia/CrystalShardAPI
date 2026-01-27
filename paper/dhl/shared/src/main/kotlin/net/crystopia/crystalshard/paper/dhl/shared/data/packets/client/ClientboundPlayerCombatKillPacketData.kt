package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.kyori.adventure.text.Component

data class ClientboundPlayerCombatKillPacketData(
    var entityId : Int,
    var message: Component
)
