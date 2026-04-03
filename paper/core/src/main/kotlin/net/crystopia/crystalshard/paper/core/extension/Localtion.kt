package net.crystopia.crystalshard.paper.core.extension

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import java.util.*

fun <T : Entity> Location.spawnEntity(entity: EntityType): T {
    return world.spawnEntity(this, entity) as T
}

fun <T : Entity> Location.spawnEntity(entity: EntityType, callback: T.() -> Unit): T {
    val entity = world.spawnEntity(this, entity) as T
    callback.invoke(entity)
    return entity
}

fun Location.teleport(entity: Entity): Location {
    entity.teleport(this)
    return this
}

fun Location.teleport(entity: UUID): Location {
    Bukkit.getEntity(entity)?.teleport(this)
    return this
}