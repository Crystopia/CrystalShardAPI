package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

import net.crystopia.crystalshard.paper.dhl.shared.enums.server.Action
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.Direction

data class PlayerActionEvent(
    var x: Int,
    var y: Int,
    var z: Int,
    var direction: Direction,
    var action: Action,
    var sequence: Int
)