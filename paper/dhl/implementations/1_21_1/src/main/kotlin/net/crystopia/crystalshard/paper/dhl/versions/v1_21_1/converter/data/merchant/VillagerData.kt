package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.merchant

import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.VillagerData
import net.minecraft.world.entity.npc.VillagerType

fun VillagerData.build(): net.minecraft.world.entity.npc.VillagerData {
    val type = when (type) {
        net.crystopia.crystalshard.paper.dhl.shared.enums.merchant.VillagerType.SNOW -> {
            VillagerType.SNOW
        }

        net.crystopia.crystalshard.paper.dhl.shared.enums.merchant.VillagerType.SWAMP -> {
            VillagerType.SWAMP
        }

        net.crystopia.crystalshard.paper.dhl.shared.enums.merchant.VillagerType.TAIGA -> {
            VillagerType.TAIGA
        }

        net.crystopia.crystalshard.paper.dhl.shared.enums.merchant.VillagerType.DESERT -> {
            VillagerType.DESERT
        }

        net.crystopia.crystalshard.paper.dhl.shared.enums.merchant.VillagerType.PLAINS -> {
            VillagerType.PLAINS
        }

        net.crystopia.crystalshard.paper.dhl.shared.enums.merchant.VillagerType.SAVANNA -> {
            VillagerType.SAVANNA
        }

        net.crystopia.crystalshard.paper.dhl.shared.enums.merchant.VillagerType.JUNGLE -> {
            VillagerType.JUNGLE
        }
    }

    return net.minecraft.world.entity.npc.VillagerData(
        type,
        profession.build(),
        level
    )
}