package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.packets
import net.minecraft.world.entity.RelativeMovement
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundPlayerPositionPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.RelativePosition

fun ClientboundPlayerPositionPacketData.relativesSet(): Set<RelativeMovement> {
    val set = mutableSetOf<RelativeMovement>()
    relatives.forEach { position ->
        set.add(net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.entities.RelativePosition.convert(position).id)
    }
    return set
}