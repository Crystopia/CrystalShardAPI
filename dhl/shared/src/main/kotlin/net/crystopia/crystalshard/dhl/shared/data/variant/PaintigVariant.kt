package net.crystopia.crystalshard.dhl.shared.data.variant

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.minecraft.network.chat.Component

data class PaintigVariant(
    var width: Int,
    var height: Int,
    var assetId: NamespacedKey,
    var title: Component? = null,
    var author: Component? = null,
    var spawnPrioritySelectors: Int
)
