package net.crystopia.crystalshard.dhl.shared.data.packets.server

data class PlayerInputEvent(
    var forward: Boolean,
    var backward: Boolean,
    var left: Boolean,
    var right: Boolean,
    var jump: Boolean,
    var shift: Boolean,
    var sprint: Boolean
)
