package net.crystopia.crystalshard.paper.dhl.shared.data.entities

import net.minecraft.world.entity.Relative

enum class RelativePosition(val id: Relative) {
    X(Relative.X),
    Y(Relative.Y),
    Z(Relative.Z),
    Y_ROT(Relative.Y_ROT),
    X_ROT(Relative.X_ROT),
    DELTA_X(Relative.DELTA_X),
    DELTA_Y(Relative.DELTA_Y),
    DELTA_Z(Relative.DELTA_Z),
    ROTATE_DELTA(Relative.ROTATE_DELTA);
}