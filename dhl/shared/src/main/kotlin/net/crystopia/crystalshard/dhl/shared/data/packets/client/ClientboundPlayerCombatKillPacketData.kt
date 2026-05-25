package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.network.chat.Component

data class ClientboundPlayerCombatKillPacketData(
    var entityId : Int,
    var message: Component
)
