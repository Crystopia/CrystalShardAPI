package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.PositionMoveRotation
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.RelativePosition
import net.minecraft.world.entity.Relative

data class ClientboundPlayerPositionPacketData(
    var entityId: Int,
    var change: PositionMoveRotation,
    var relatives: MutableSet<RelativePosition>
) {
    fun relativesSet(): Set<Relative> {
        val set = mutableSetOf<Relative>()
        relatives.forEach { position ->
            set.add(position.id)
        }
        return set
    }
}
