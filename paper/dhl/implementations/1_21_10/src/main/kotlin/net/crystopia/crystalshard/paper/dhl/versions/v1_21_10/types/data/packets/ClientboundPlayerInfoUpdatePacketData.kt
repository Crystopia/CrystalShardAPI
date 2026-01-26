package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction
import org.bukkit.entity.Player
import java.util.*

data class ClientboundPlayerInfoUpdatePacketData(
    val serverPlayer: Player,
    val actions: EnumSet<InfoUpdateAction>
)
