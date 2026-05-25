package net.crystopia.crystalshard.dhl.shared.data.packets.client

data class ClientboundPlayerRotationPacketData(
    var yRot: Float,
    var relativeY: Boolean,
    var xRot: Float,
    var relativeX: Boolean
)
