package net.crystopia.crystalshard.paper.core.extension

import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType


fun <T : Entity> World.spawnEntity(
    x: Double,
    y: Double,
    z: Double,
    type: EntityType,
): T {
    val spawned = spawnEntity(Location(this, x, y, z), type) as T
    return spawned
}

fun <T : Entity> World.spawnEntity(
    x: Double,
    y: Double,
    z: Double,
    type: EntityType,
    entity: T.() -> Unit
): T {
    val spawned = spawnEntity(Location(this, x, y, z), type) as T
    entity.invoke(spawned)
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