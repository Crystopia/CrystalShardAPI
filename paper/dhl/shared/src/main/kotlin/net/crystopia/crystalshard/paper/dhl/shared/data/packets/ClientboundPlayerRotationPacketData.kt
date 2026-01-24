package net.crystopia.crystalshard.paper.dhl.shared.data.packets

data class ClientboundPlayerRotationPacketData(
    var yRot: Float,
    var relativeY: Boolean,
    var xRot: Float,
    var relativeX: Boolean
)
