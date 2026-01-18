package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockEntityType
import net.minecraft.nbt.CompoundTag

data class ClientboundBlockEntityDataPacketData(
    var blockPos: net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockPos,
    var type: BlockEntityType,
    var nbt: CompoundTag
)
