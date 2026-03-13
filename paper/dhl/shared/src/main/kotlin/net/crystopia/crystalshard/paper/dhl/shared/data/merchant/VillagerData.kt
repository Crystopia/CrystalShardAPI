package net.crystopia.crystalshard.paper.dhl.shared.data.merchant

import net.crystopia.crystalshard.paper.dhl.shared.enums.merchant.VillagerType

data class VillagerData(
    var type: VillagerType,
    var profession: VillagerProfession,
    var level : Int
)
