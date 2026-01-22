package net.crystopia.crystalshard.paper.dhl.shared.data.entities

data class EffectInstance(
    var effect: Effect,
    var duration: Int,
    var amplifier: Double,
    var ambient: Boolean,
    var visible: Boolean,
    var showIcon: Boolean,
)
