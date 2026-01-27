package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction
import org.bukkit.entity.Player

data class ClientboundPlayerInfoUpdatePacketData(
    val serverPlayer: Player,
    val actions: MutableList<InfoUpdateAction>
)
