package net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.merchant

import net.crystopia.crystalshard.dhl.shared.data.merchant.PoiType

fun PoiType.build(): net.minecraft.world.entity.ai.village.poi.PoiType {
    val states = matchingStates.map { material ->
        material.block.defaultBlockState()
    }.toSet()
    return net.minecraft.world.entity.ai.village.poi.PoiType(
        states,
        maxTickets,
        validRange
    )
}