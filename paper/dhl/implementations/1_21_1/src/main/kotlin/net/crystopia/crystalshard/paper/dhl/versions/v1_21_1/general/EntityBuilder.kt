package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general

import com.mojang.authlib.GameProfile
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.entities.IEntityBuilder
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ClientInformation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.craftbukkit.CraftServer
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.entity.CraftEntityType
import java.util.*

object EntityBuilder : IEntityBuilder {

    override fun createEntityInstance(
        type: org.bukkit.entity.EntityType,
        location: Location
    ): net.minecraft.world.entity.Entity {
        val mcType = CraftEntityType.bukkitToMinecraft(type)
        val instance = mcType.create(
            (location.world as CraftWorld).handle,
        )
        return instance!!
    }

    override fun createServerPlayer(
        world: World, name: String
    ): ServerPlayer {
        val uuid = UUID.randomUUID()
        val minecraftServer: MinecraftServer = (Bukkit.getServer() as CraftServer).server
        val serverPlayer = ServerPlayer(
            minecraftServer, (world as CraftWorld).handle, GameProfile(uuid, name), ClientInformation.createDefault()
        )

        return serverPlayer
    }

    override fun createDisplayEntity(type: EntityType<*>, location: Location): Display? {
        when (type) {
            EntityType.TEXT_DISPLAY -> {
                val entity = Display.TextDisplay(type, (location.world as CraftWorld).handle)
                return entity as Display.TextDisplay
            }

            EntityType.ITEM_DISPLAY -> {

                val entity = Display.ItemDisplay(type, (location.world as CraftWorld).handle)
                return entity as Display.ItemDisplay
            }

            EntityType.BLOCK_DISPLAY -> {
                val entity = Display.BlockDisplay(type, (location.world as CraftWorld).handle)
                return entity as Display.BlockDisplay
            }

            else -> IllegalArgumentException("Use a valid Display Entity type.")
        }

        return null
    }
}