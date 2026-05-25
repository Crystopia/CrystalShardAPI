package net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.data.merchant

import net.crystopia.crystalshard.dhl.shared.data.merchant.PoiType

fun PoiType.build(): net.minecraft.world.entity.ai.village.poi.PoiType {
    return net.minecraft.world.entity.ai.village.poi.PoiType(
        matchingStates,
        maxTickets,
        validRange
    )
}