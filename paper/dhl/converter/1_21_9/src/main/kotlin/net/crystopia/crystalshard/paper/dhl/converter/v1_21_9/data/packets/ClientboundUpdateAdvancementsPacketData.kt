package net.crystopia.crystalshard.paper.dhl.converter.v1_21_9.data.packets

import net.minecraft.advancements.AdvancementProgress
import org.bukkit.NamespacedKey
import org.bukkit.advancement.Advancement
import org.bukkit.craftbukkit.advancement.CraftAdvancement

fun advancementToShard(added: MutableList<Advancement>): MutableMap<net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey, net.minecraft.advancements.Advancement> {
    return added.map { key ->
        return@map Pair(
            net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(key.key.namespace, key.key.key),
            (key as CraftAdvancement).handle.value
        )
    }.toMap().toMutableMap()
}

fun advancementProgressToShard(progress: MutableMap<NamespacedKey, org.bukkit.advancement.AdvancementProgress>): MutableMap<net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey, AdvancementProgress> {
    return progress.map {
        val progress = AdvancementProgress()
        // TODO
        return@map Pair(
            net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(it.key.namespace, it.key.key),
            progress
        )
    }.toMap().toMutableMap()
}