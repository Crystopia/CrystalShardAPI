package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.world.level.chunk.LevelChunk
import java.util.*

data class ClientboundLevelChunkWithLightPacketData(
    var levelChunk: LevelChunk,
    var skyLight: BitSet,
    var blockLight: BitSet,
    var isSkyLight: Boolean,
    var isBlockLight: Boolean
)
