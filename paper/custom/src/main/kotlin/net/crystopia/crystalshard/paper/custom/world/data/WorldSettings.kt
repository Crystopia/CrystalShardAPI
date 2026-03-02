package net.crystopia.crystalshard.paper.custom.world.data

import org.bukkit.World
import org.bukkit.WorldType
import org.bukkit.generator.BiomeProvider
import org.bukkit.generator.ChunkGenerator

data class WorldSettings(
    var seed: Long?,
    var environment: World.Environment?,
    var type: WorldType?,
    var generateStructures: Boolean?,
    var hardcore: Boolean?,
    var bonusChest: Boolean?,
)
