package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundPlayerPositionPacketData
import net.minecraft.world.entity.Relative

fun ClientboundPlayerPositionPacketData.relativesSet(): Set<Relative> {
    val set = mutableSetOf<Relative>()
    relatives.forEach { position ->
        set.add(net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.entities.RelativePosition.convert(position).id)
    }
    return set
}