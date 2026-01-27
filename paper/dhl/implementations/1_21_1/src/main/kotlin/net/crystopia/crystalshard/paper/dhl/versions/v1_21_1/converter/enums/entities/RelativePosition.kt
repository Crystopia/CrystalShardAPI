package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.entities

import net.minecraft.world.entity.RelativeMovement


enum class RelativePosition(val id: RelativeMovement) {
    X(RelativeMovement.X),
    Y(RelativeMovement.Y),
    Z(RelativeMovement.Z),
    Y_ROT(RelativeMovement.Y_ROT),
    X_ROT(RelativeMovement.X_ROT);

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.entities.RelativePosition): RelativePosition {
            return valueOf(
                type.name
            )
        }
    }

}