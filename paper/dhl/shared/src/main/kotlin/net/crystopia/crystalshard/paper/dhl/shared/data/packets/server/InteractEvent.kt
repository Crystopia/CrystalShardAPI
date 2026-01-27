package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ClickActionType

data class InteractEvent(
    var entityId: Int,
    var isAttack: Boolean,
    var sneakKeyPressed: Boolean,
    var clickActionType: ClickActionType
)
