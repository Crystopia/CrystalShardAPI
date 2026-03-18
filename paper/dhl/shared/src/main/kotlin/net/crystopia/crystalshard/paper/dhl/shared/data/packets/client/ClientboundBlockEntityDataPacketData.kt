package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.minecraft.world.level.block.entity.BlockEntityType
import org.bukkit.block.BlockType

data class ClientboundBlockEntityDataPacketData(
    var blockPos: BlockPos,
    var type: net.crystopia.crystalshard.paper.dhl.shared.enums.entities.BlockEntityType,
    var nbt: String
)
