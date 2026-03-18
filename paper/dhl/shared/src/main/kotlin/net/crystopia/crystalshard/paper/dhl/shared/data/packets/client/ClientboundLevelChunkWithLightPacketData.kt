package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.chunk.LevelChunk
import java.util.*

data class ClientboundLevelChunkWithLightPacketData(
    var lightChunk: LevelChunk,
    var skyLight: BitSet,
    var blockLight: BitSet,
    var isSkyLight: Boolean,
    var isBlockLight: Boolean
)
