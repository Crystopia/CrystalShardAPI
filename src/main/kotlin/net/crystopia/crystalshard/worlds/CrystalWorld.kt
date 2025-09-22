package net.crystopia.crystalshard.worlds

import net.crystopia.crystalshard.entities.CrystalEntity
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType


/**
 *
 * Shorter Methods to interact with a World.
 * More Methods will follow!!!
 *
 */
class CrystalWorld(
    private val handle: World,
) {

    fun spawnEntity(
        x: Double,
        y: Double,
        z: Double,
        type: EntityType,
        settings: CrystalEntity.(crystalEntity: CrystalEntity) -> Unit = {}
    ): Entity {
        val spawned = handle.spawnEntity(Location(handle, x, y, z), type)
        CrystalEntity(spawned)

        return spawned
    }

}