package net.crystopia.crystalshard.paper.dhl.types.chunk

import org.bukkit.World

data class LevelChunk(
    var world : World,
    var x: Int,
    var y: Int
)
