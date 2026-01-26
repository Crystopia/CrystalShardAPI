package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction
import org.bukkit.entity.Player
import java.util.*

data class ClientboundPlayerInfoUpdatePacketData(
    val serverPlayer: Player,
    val actions: MutableList<InfoUpdateAction>
)
