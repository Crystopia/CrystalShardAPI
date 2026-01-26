package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import org.bukkit.World

data class ClientboundSetDefaultSpawnPositionPacketData(
    var world: World,
    var pos: BlockPos,
    var yaw: Float,
    var pitch: Float,
    var angle: Float
)
