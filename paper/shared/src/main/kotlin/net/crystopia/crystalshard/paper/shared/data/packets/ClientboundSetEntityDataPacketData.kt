package net.crystopia.crystalshard.paper.shared.data.packets

import net.minecraft.network.syncher.SynchedEntityData

data class ClientboundSetEntityDataPacketData(
    var entityId: Int,
    var entityData: MutableList<SynchedEntityData.DataValue<*>>
)