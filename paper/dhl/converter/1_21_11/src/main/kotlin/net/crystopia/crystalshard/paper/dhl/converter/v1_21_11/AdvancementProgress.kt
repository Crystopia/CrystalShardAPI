package net.crystopia.crystalshard.paper.dhl.converter.v1_21_11

import net.crystopia.crystalshard.dhl.shared.builder.AdvancementProgressBuilder
import net.minecraft.advancements.AdvancementProgress
import org.bukkit.NamespacedKey

fun AdvancementProgressBuilder.PAPER_1_21_11(progress: MutableMap<NamespacedKey, org.bukkit.advancement.AdvancementProgress>): MutableMap<net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey, AdvancementProgress> {
    return progress.map {
        val progress = AdvancementProgress()
        // TODO
        return@map Pair(
            net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(it.key.namespace, it.key.key),
            progress
        )
    }.toMap().toMutableMap()
}