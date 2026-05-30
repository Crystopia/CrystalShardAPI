package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.entities.PositionMoveRotation
import net.crystopia.crystalshard.dhl.shared.enums.entities.RelativePosition
import net.minecraft.world.entity.Entity

data class ClientboundPlayerPositionPacketData(
    var teleportId: Int ,
    var change: PositionMoveRotation,
    var relatives: MutableSet<RelativePosition>
)
