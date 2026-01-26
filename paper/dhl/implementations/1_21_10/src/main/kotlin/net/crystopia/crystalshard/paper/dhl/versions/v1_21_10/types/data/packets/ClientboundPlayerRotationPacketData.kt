package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

data class ClientboundPlayerRotationPacketData(
    var yRot: Float,
    var relativeY: Boolean,
    var xRot: Float,
    var relativeX: Boolean
)
