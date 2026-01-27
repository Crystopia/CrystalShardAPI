package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.entities

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

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.entities.RelativePosition): RelativePosition {
            return valueOf(
                type.name
            )
        }
    }

}