package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.custom.Location
import net.minecraft.world.entity.Entity

data class ClientboundTeleportEntityPacketData(
    var entity: Entity,
    var location: Location,
    var onGround : Boolean
)
