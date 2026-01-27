package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.PositionMoveRotation
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.RelativePosition
import net.minecraft.world.entity.Relative

data class ClientboundPlayerPositionPacketData(
    var entityId: Int,
    var change: PositionMoveRotation,
    var relatives: MutableSet<RelativePosition>
) {

}
