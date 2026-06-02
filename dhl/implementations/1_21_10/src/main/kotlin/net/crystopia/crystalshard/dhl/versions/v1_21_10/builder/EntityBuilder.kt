package net.crystopia.crystalshard.dhl.versions.v1_21_10.builder

import com.mojang.authlib.GameProfile
import net.crystopia.crystalshard.dhl.shared.interfaces.entities.IEntityBuilder
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ClientInformation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level
import java.util.*

object EntityBuilder : IEntityBuilder {

    override fun createEntityInstance(
        type: EntityType<*>,
        location: net.crystopia.crystalshard.dhl.shared.data.custom.Location
    ): net.minecraft.world.entity.Entity {
        val instance = type.create(
            location.world as ServerLevel,
            EntitySpawnReason.COMMAND,
        )
        return instance!!
    }

    override fun createServerPlayer(
        world: Level, name: String, server: MinecraftServer
    ): ServerPlayer {
        val uuid = UUID.randomUUID()
        val serverPlayer = ServerPlayer(
            server, world as ServerLevel, GameProfile(uuid, ""), ClientInformation.createDefault()
        )

        return serverPlayer
    }

    override fun createDisplayEntity(
        type: EntityType<*>,
        location: net.crystopia.crystalshard.dhl.shared.data.custom.Location
    ): Display? {
        when (type) {
            EntityType.TEXT_DISPLAY -> {
                val entity = Display.TextDisplay(type, location.world as ServerLevel)
                return entity as Display.TextDisplay
            }

            EntityType.ITEM_DISPLAY -> {

                val entity = Display.ItemDisplay(type, location.world as ServerLevel)
                return entity as Display.ItemDisplay
            }

            EntityType.BLOCK_DISPLAY -> {
                val entity = Display.BlockDisplay(type, location.world as ServerLevel)
                return entity as Display.BlockDisplay
            }

            else -> IllegalArgumentException("Use a valid Display Entity type.")
        }

        return null
    }
}