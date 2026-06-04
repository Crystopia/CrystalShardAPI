package net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.merchant

import com.google.common.base.Predicates
import com.google.common.collect.ImmutableSet
import net.crystopia.crystalshard.dhl.shared.data.merchant.VillagerProfession
import net.minecraft.core.Holder

fun VillagerProfession.build(): net.minecraft.world.entity.npc.villager.VillagerProfession {
    return net.minecraft.world.entity.npc.villager.VillagerProfession(
        name,
        Predicates.`in`(mutableListOf(Holder.direct(heldJobSite.build()))),
        Predicates.`in`(mutableListOf(Holder.direct(acquirableJobSite.build()))),
        ImmutableSet.copyOf(requestedItems.map { stack -> stack.item }.toSet()),
        ImmutableSet.copyOf(blocks.map { block -> block }.toSet()),
        workSound
    )
}