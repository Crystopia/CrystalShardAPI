package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.Entity

data class ClientboundSetEntityDataPacketData(
    var entity: Entity,
    var entityData: MutableList<SynchedEntityData.DataValue<*>>
)