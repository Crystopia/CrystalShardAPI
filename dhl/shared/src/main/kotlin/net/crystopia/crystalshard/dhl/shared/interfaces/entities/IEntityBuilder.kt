package net.crystopia.crystalshard.dhl.shared.interfaces.entities

import net.crystopia.crystalshard.dhl.shared.data.custom.Location
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level

interface IEntityBuilder {

    fun createEntityInstance(
        type: EntityType<*>, location: Location
    ): net.minecraft.world.entity.Entity

    fun createServerPlayer(
        world: Level, name: String, server: MinecraftServer
    ): ServerPlayer

    fun createDisplayEntity(
        type: EntityType<*>,
        location: Location,
    ): Display?

}