package net.crystopia.crystalshard.paper.shared.interfaces.entities

import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.World

interface IEntityBuilder {

    fun createServerPlayer(
        world: World, key: NamespacedKey, name: String
    ): ServerPlayer

    fun createDisplayEntity(
        type: EntityType<*>,
        location: Location,
    ): Display?

}