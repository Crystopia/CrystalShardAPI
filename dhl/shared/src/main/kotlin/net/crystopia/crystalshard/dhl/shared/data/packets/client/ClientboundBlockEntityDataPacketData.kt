package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos

data class ClientboundBlockEntityDataPacketData(
    var blockPos: BlockPos,
    var type: net.crystopia.crystalshard.dhl.shared.enums.entities.BlockEntityType,
    var nbt: String
)
