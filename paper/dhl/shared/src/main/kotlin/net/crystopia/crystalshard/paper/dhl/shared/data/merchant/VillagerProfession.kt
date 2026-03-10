package net.crystopia.crystalshard.paper.dhl.shared.data.merchant

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.inventory.ItemStack

data class VillagerProfession(
    var name: Component,
    var heldJobSite: PoiType,
    var acquirableJobSite: PoiType,
    var requestedItems: MutableSet<ItemStack>,
    var blocks: MutableSet<Material>,
    var workSound: Sound
)