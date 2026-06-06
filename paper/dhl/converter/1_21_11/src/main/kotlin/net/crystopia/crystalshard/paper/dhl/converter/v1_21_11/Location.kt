package net.crystopia.crystalshard.paper.dhl.converter.v1_21_11

import net.crystopia.crystalshard.dhl.shared.builder.LocationBuilder
import net.crystopia.crystalshard.dhl.shared.data.custom.Location
import org.bukkit.craftbukkit.CraftWorld

fun LocationBuilder.PAPER_1_21_11(location: org.bukkit.Location): Location {
    return Location(
        (location.world as CraftWorld).handle, location.z, location.y, location.z, location.yaw, location.pitch
    )
}