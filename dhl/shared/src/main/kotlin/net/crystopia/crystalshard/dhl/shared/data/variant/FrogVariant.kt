package net.crystopia.crystalshard.dhl.shared.data.variant

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey

data class FrogVariant(
    var type: NamespacedKey,
    var spawnPrioritySelectors: Int
)
