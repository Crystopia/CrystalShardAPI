package net.crystopia.crystalshard.builder

import com.mojang.authlib.GameProfile
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ClientInformation
import net.minecraft.server.level.ServerPlayer
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.craftbukkit.CraftServer
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.entity.EntityType
import java.util.*


object EntityBuilder {

    inline fun <reified T : Any> spawnEntity(
        world: World,
        location: Location,
        type: EntityType,
    ): T {
        val entity = world.spawnEntity(location, type)
        return entity as T
    }

    fun spawnNpc(
        world: World, callback: ServerPlayer.() -> Unit = {}
    ): ServerPlayer {
        var npc: ServerPlayer? = null
        val minecraftServer: MinecraftServer = (Bukkit.getServer() as CraftServer).server
        val gameProfile = GameProfile(UUID.randomUUID(), UUID.randomUUID().toString().split("-")[0])
        npc =
            ServerPlayer(minecraftServer, (world as CraftWorld).handle, GameProfile(UUID.randomUUID(), ""), ClientInformation.createDefault())
        npc.gameProfile = gameProfile

        callback(npc)
        return npc
    }
}