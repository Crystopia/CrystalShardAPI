package net.crystopia.crystalshard.paper.core.extension

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import java.util.*

fun <T : org.bukkit.entity.Entity> Location.spawnEntity(entity: EntityType): T {
    return world.spawnEntity(this, entity) as T
}

fun Location.spawnEntity(entity: EntityType): Entity {
    return world.spawnEntity(this, entity)
}

fun Location.teleport(entity: Entity): Location {
    entity.teleport(this)
    return this
}

fun Location.teleport(entity: UUID): Location {
    Bukkit.getEntity(entity)?.teleport(this)
    return this
}