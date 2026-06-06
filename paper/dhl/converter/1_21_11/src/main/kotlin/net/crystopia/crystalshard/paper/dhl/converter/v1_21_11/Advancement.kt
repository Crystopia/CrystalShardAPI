package net.crystopia.crystalshard.paper.dhl.converter.v1_21_11

import net.crystopia.crystalshard.dhl.shared.builder.AdvancementBuilder
import net.crystopia.crystalshard.dhl.shared.builder.AdvancementProgressBuilder
import net.minecraft.advancements.AdvancementProgress
import org.bukkit.NamespacedKey
import org.bukkit.advancement.Advancement
import org.bukkit.craftbukkit.advancement.CraftAdvancement

fun AdvancementBuilder.PAPER_1_21_11(added: MutableList<Advancement>): MutableMap<net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey, net.minecraft.advancements.Advancement> {
    return added.map { key ->
        return@map Pair(
            net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(key.key.namespace, key.key.key),
            (key as CraftAdvancement).handle.value
        )
    }.toMap().toMutableMap()
}