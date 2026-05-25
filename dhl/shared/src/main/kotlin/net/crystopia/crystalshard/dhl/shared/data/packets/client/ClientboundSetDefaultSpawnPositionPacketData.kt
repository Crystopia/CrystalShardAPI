package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos
import net.minecraft.world.level.Level

data class ClientboundSetDefaultSpawnPositionPacketData(
    var world: Level,
    var pos: BlockPos,
    var yaw: Float,
    var pitch: Float,
    var angle: Float
)
