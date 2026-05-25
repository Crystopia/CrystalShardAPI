package net.crystopia.crystalshard.dhl.shared.data.variant

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.minecraft.world.entity.animal.wolf.WolfVariant

data class WolfVariant(
    var type: NamespacedKey,
    var spawnPrioritySelectors: Int
)
