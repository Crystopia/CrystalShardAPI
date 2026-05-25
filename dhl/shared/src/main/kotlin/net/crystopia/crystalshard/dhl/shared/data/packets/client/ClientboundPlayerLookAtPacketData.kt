package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.enums.entities.LookAnchor
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level

data class ClientboundPlayerLookAtPacketData(
    var entity: Entity,
    var world: Level,
    var fromAnchor: LookAnchor,
    var toAnchor: LookAnchor,
    var x: Double,
    var y: Double,
    var z: Double
)
