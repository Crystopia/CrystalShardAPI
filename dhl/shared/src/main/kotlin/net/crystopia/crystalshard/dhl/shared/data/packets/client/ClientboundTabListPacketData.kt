package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.network.chat.Component

data class ClientboundTabListPacketData(
    var footer: Component,
    var header: Component
)
