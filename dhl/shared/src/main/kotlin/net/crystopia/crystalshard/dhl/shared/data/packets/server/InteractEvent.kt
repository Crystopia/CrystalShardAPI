package net.crystopia.crystalshard.dhl.shared.data.packets.server

import net.crystopia.crystalshard.dhl.shared.enums.server.ClickActionType

data class InteractEvent(
    var entityId: Int,
    var isAttack: Boolean,
    var sneakKeyPressed: Boolean,
    var clickActionType: ClickActionType
)
