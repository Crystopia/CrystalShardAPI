package net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.converter.data.merchant

import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.PoiType
import org.bukkit.craftbukkit.block.CraftBlockType

fun PoiType.build(): net.minecraft.world.entity.ai.village.poi.PoiType {
    val states = matchingStates.map { material ->
        CraftBlockType.bukkitToMinecraft(material).defaultBlockState()
    }.toSet()
    return net.minecraft.world.entity.ai.village.poi.PoiType(
        states,
        maxTickets,
        validRange
    )
}