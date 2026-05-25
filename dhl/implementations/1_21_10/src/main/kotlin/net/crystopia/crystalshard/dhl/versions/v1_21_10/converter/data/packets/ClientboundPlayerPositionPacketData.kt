package net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.data.packets

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerPositionPacketData
import net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.enums.entities.RelativePosition
import net.minecraft.world.entity.Relative

fun ClientboundPlayerPositionPacketData.relativesSet(): Set<Relative> {
    val set = mutableSetOf<Relative>()
    relatives.forEach { position ->
        set.add(RelativePosition.convert(position).id)
    }
    return set
}