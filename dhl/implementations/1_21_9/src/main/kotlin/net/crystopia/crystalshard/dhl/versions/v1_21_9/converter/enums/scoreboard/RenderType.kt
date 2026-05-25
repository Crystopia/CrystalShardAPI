package net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.enums.scoreboard

import net.minecraft.world.scores.criteria.ObjectiveCriteria

enum class RenderType(var id: ObjectiveCriteria.RenderType) {
    INTEGER(ObjectiveCriteria.RenderType.INTEGER), HEARTS(ObjectiveCriteria.RenderType.HEARTS);

    companion object {
        fun convert(type: net.crystopia.crystalshard.dhl.shared.enums.scoreboard.RenderType): RenderType {
            return valueOf(
                type.name
            )
        }
    }

}