package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityMetadata
import net.minecraft.network.syncher.SynchedEntityData

data class ClientboundSetEntityDataPacketData(
    var entityId: Int,
    var entityData: MutableList<EntityMetadata<*>>
)