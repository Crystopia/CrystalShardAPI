package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.minecraft.network.syncher.SynchedEntityData

data class ClientboundSetEntityDataPacketData(
    var entityId: Int,
    var entityData: MutableList<SynchedEntityData.DataValue<*>>
)