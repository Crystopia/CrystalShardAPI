package net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.data.packets
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerPositionPacketData
import net.minecraft.world.entity.RelativeMovement

fun ClientboundPlayerPositionPacketData.relativesSet(): Set<RelativeMovement> {
    val set = mutableSetOf<RelativeMovement>()
    relatives.forEach { position ->
        set.add(net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.enums.entities.RelativePosition.convert(position).id)
    }
    return set
}