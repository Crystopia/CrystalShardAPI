package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.kyori.adventure.text.Component

data class ClientboundTabListPacketData(
    var footer: Component,
    var header: Component
)
