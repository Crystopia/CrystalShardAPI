package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

import net.crystopia.crystalshard.paper.dhl.shared.enums.server.CommandAction

data class PlayerCommandEvent(
    var entityId: Int,
    var action: CommandAction,
    var data: Int
)