package net.crystopia.crystalshard.builder

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType

object EntityBuilder {

    inline fun <reified T : Entity> spawnEntity(
        world: World,
        location: Location,
        type: EntityType,
    ): T {

        val entity = world.spawnEntity(location, type)
        return entity as T
    }
    
}