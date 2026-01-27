package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.world

import net.minecraft.core.Vec3i
import net.minecraft.world.phys.Vec3

fun net.crystopia.crystalshard.paper.dhl.shared.data.world.Vec3.build(): Vec3 {
    return Vec3.atLowerCornerWithOffset(
        Vec3i(
            vec3i.x.toInt(), vec3i.y.toInt(), vec3i.z.toInt()
        ), offsetX, offsetY, offsetZ
    )
}
