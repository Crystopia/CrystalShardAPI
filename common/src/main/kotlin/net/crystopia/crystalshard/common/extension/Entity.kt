package net.crystopia.crystalshard.common.extension

import org.bukkit.entity.Damageable
import org.bukkit.entity.Entity

fun Entity.kill(): Boolean {
    this.remove()
    if (this is Damageable) {
        this.health = 0.0
        return true
    }
    return false
}
    

