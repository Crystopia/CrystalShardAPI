package net.crystopia.crystalshard.extension

import org.bukkit.entity.Damageable
import org.bukkit.entity.Entity


/**
 *
 * Cleaner Entities from Minecraft with simpler Methods.
 *
 */

fun Entity.kill(): Boolean {
    if (this is Damageable) {
        this.health = 0.0
        return true
    }
    return false
}

