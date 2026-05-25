package net.crystopia.crystalshard.dhl.shared.data.chunk

import net.minecraft.world.level.Level

data class LevelChunk(
    var world : Level,
    var x: Int,
    var y: Int
)
