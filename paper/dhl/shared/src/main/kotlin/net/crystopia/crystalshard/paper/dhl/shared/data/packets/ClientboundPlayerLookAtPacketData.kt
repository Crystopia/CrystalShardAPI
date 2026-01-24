package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.LookAnchor
import org.bukkit.World

data class ClientboundPlayerLookAtPacketData(
    var entityId: Int,
    var world: World,
    var fromAnchor: LookAnchor,
    var toAnchor: LookAnchor,
    var x: Double,
    var y: Double,
    var z: Double
)
