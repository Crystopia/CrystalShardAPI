package net.crystopia.crystalshard.paper.dhl.shared.interfaces.entities

import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import org.bukkit.Location
import org.bukkit.World

interface IEntityBuilder {

    fun createEntityInstance(
        type: org.bukkit.entity.EntityType, location: Location
    ): net.minecraft.world.entity.Entity

    fun createServerPlayer(
        world: World, name: String
    ): ServerPlayer

    fun createDisplayEntity(
        type: EntityType<*>,
        location: Location,
    ): Display?

}