package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.PositionMoveRotation
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.RelativePosition

data class ClientboundPlayerPositionPacketData(
    var entityId: Int,
    var change: PositionMoveRotation,
    var relatives: MutableSet<RelativePosition>
) {

}
