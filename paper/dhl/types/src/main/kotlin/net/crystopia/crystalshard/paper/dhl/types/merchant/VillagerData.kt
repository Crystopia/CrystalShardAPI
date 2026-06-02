package net.crystopia.crystalshard.paper.dhl.types.merchant

import net.crystopia.crystalshard.dhl.shared.enums.merchant.VillagerType

data class VillagerData(
    var type: VillagerType,
    var profession: VillagerProfession,
    var level : Int
)
