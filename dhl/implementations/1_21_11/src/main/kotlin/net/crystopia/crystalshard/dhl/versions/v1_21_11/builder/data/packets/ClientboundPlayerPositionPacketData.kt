package net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.packets

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerPositionPacketData
import net.minecraft.world.entity.Relative

fun ClientboundPlayerPositionPacketData.relativesSet(): Set<Relative> {
    val set = mutableSetOf<Relative>()
    relatives.forEach { position ->
        set.add(net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.enums.entities.RelativePosition.convert(position).id)
    }
    return set
}