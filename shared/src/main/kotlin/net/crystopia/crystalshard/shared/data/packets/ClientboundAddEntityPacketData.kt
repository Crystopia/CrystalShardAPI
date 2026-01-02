package net.crystopia.crystalshard.shared.data.packets

import net.minecraft.world.entity.EntityType
import net.minecraft.world.phys.Vec3
import org.bukkit.Location
import java.util.*

data class ClientboundAddEntityPacketData(
    var entityId: Int,
    var entityUUID: UUID,
    var location: Location,
    var entityType: EntityType<*>,
    var data: Int,
    var deltaMovement: Vec3 = Vec3.ZERO,
    var yHeadRot: Double = 0.0
)