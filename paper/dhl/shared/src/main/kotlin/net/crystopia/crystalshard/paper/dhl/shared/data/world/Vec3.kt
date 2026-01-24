package net.crystopia.crystalshard.paper.dhl.shared.data.world

import net.minecraft.world.phys.Vec3

data class Vec3(
    var vec3i: Vec3i,
    var offsetX: Double,
    var offsetY: Double,
    var offsetZ: Double
) {
    fun build(): Vec3 {
        return Vec3.atLowerCornerWithOffset(
            net.minecraft.core.Vec3i(
                vec3i.x.toInt(), vec3i.y.toInt(), vec3i.z.toInt()
            ), offsetX, offsetY, offsetZ
        )
    }
}