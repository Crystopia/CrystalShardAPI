package net.crystopia.crystalshard.dhl.shared.data.merchant

import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block


data class VillagerProfession(
    var name: Component,
    var heldJobSite: PoiType,
    var acquirableJobSite: PoiType,
    var requestedItems: MutableSet<ItemStack>,
    var blocks: MutableSet<Block>,
    var workSound: SoundEvent
)