package net.crystopia.crystalshard.common.custom

import gg.flyte.twilight.extension.spawnEntity
import org.bukkit.Location
import org.bukkit.entity.EntityType

object CrystalEntity {

    fun <T : Any> createEntity(
        location: Location,
        type: EntityType,
        callback: (entity: T) -> Unit
    ): T {
        val entity = location.spawnEntity(type)
        callback(entity as T)
        return entity as T
    }

}