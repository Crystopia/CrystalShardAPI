package net.crystopia.crystalshard.dhl.shared.data.packets.server

import net.crystopia.crystalshard.dhl.shared.enums.server.CommandAction

data class PlayerCommandEvent(
    var entityId: Int,
    var action: CommandAction,
    var data: Int
)