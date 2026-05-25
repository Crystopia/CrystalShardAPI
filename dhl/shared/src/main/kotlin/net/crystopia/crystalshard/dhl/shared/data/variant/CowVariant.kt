package net.crystopia.crystalshard.dhl.shared.data.variant

import net.minecraft.world.entity.animal.cow.CowVariant

data class CowVariant(
    var type: net.crystopia.crystalshard.dhl.shared.enums.entities.CowVariant,
    var spawnPrioritySelectors: Int
)
