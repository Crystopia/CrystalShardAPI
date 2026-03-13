package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.merchant

import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.VillagerData
import net.minecraft.core.Holder

fun VillagerData.build(): net.minecraft.world.entity.npc.VillagerData {
    return net.minecraft.world.entity.npc.VillagerData(
        Holder.direct(net.minecraft.world.entity.npc.VillagerType()),
        Holder.direct(
            profession.build()
        ),
        level
    )
}