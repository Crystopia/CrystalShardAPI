package net.crystopia.crystalshard.paper.util.world.data

import org.bukkit.World
import org.bukkit.WorldType

data class WorldSettings(
    var seed: Long?,
    var environment: World.Environment?,
    var type: WorldType?,
    var generateStructures: Boolean?,
    var hardcore: Boolean?,
    var bonusChest: Boolean?,
)
