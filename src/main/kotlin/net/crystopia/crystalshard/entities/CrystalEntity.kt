package net.crystopia.crystalshard.entities

import org.bukkit.entity.Damageable
import org.bukkit.entity.Entity


/**
 *
 * Cleaner Entities from Minecraft with simpler Methods.
 *
 */
open class CrystalEntity(
    private var handle: Entity
) {

    fun kill(): Boolean {
        if (handle is Damageable) {
            (handle as Damageable).health = 0.0
            return true
        }
        return false
    }
}
