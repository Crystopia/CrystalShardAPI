package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.minecraft.nbt.CompoundTag
import org.bukkit.block.BlockType

data class ClientboundBlockEntityDataPacketData(
    var blockPos: BlockPos,
    var type: BlockType,
    var nbt: MutableMap<String, Any>
)
