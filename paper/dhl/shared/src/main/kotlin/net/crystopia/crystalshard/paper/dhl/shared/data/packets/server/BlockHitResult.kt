package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

import net.crystopia.crystalshard.paper.dhl.shared.enums.server.Direction
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ItemUseType

data class BlockHitResult(
    var direction: Direction,
    var x: Int,
    var y: Int,
    var z: Int,
    var type: ItemUseType,
    var inside: Boolean,
    var worldBorderHit: Boolean
)
