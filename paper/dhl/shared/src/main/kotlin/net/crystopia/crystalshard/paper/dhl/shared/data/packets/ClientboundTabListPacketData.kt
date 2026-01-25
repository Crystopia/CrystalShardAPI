package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.kyori.adventure.text.Component

data class ClientboundTabListPacketData(
    var footer: Component,
    var header: Component
)
