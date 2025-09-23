package net.crystopia.crystalshard.extension

import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType


/**
 *
 * Shorter Methods to interact with a World.
 * More Methods will follow!!!
 *
 */
fun World.spawnEntity(    x: Double,
    y: Double,
    z: Double,
    type: EntityType,
): Entity {
    val spawned = spawnEntity(Location(this, x, y, z), type)
    return spawned
}

fun World.broadcast(location: Location, radius: Double, sound: Sound, volume: Float, pitch: Float) {
    val world = location.world ?: return
    for (player in world.players) {
        if (player.location.distanceSquared(location) <= radius * radius) {
            player.playSound(location, sound, volume, pitch)
        }
    }
}