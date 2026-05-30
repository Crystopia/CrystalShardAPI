package net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.data.world

import net.minecraft.core.Vec3i
import net.minecraft.world.phys.Vec3

fun net.crystopia.crystalshard.dhl.shared.data.world.Vec3.build(): Vec3 {
    return Vec3(
        x,y,z
    )
}
