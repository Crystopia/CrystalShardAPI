package net.crystopia.crystalshard.dhl.shared.data.variant

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey

data class CatVariant(
    var type: NamespacedKey,
    var spawnPrioritySelectors: Int
)
