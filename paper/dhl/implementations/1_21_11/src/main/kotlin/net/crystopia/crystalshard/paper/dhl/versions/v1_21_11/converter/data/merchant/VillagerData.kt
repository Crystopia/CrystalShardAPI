package net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.converter.data.merchant

import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.VillagerData
import net.minecraft.core.Holder

fun VillagerData.build(): net.minecraft.world.entity.npc.villager.VillagerData {
    return net.minecraft.world.entity.npc.villager.VillagerData(
        Holder.direct(net.minecraft.world.entity.npc.villager.VillagerType()),
        Holder.direct(
            profession.build()
        ),
        level
    )
}