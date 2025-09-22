package net.crystopia.crystalshard.sound

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player


/**
 *
 * Short Sound API to simply interact with the sounds in Minecraft.
 *
 */
class CrystalSound(val sound: String, val volume: Float, val pitch: Float) {
    fun player(player: Player) {
        player.playSound(player.location, sound, volume, pitch)
    }

    fun location(location: Location) {
        location.world?.playSound(location, sound, volume, pitch)
    }

    fun players(players: Iterable<Player>) {
        for (player in players) {
            player(player)
        }
    }

    fun world(world: World, location: Location) {
        world.playSound(location, sound, volume, pitch)
    }

    fun broadcast(location: Location, radius: Double) {
        val world = location.world ?: return
        for (player in world.players) {
            if (player.location.distanceSquared(location) <= radius * radius) {
                player.playSound(location, sound, volume, pitch)
            }
        }
    }
}
