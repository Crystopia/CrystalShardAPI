package net.crystopia.crystalshard.paper.core.extension

import org.bukkit.entity.Damageable
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity

fun LivingEntity.kill(): Boolean {
    this.remove()
    this.health = 0.0
    return true
}
    

