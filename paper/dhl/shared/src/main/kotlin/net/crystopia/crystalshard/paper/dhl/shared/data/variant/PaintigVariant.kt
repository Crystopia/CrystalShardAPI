package net.crystopia.crystalshard.paper.dhl.shared.data.variant

import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey

data class PaintigVariant(
    var width: Int,
    var height: Int,
    var assetId: NamespacedKey,
    var title: Component? = null,
    var author: Component? = null,
    var spawnPrioritySelectors: Int
)
