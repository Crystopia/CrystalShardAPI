package net.crystopia.crystalshard.builder

import com.mojang.authlib.GameProfile
import net.crystopia.crystalshard.extra.npc.INpc
import net.crystopia.crystalshard.extra.npc.Npc
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ClientInformation
import net.minecraft.server.level.ServerPlayer
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.NamespacedKey
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

    fun createNpc(
        world: World,
        key: NamespacedKey,
        name: String?,
        callback: INpc.() -> Unit = {}
    ): INpc {
        val minecraftServer: MinecraftServer = (Bukkit.getServer() as CraftServer).server
        val serverPlayer = ServerPlayer(
            minecraftServer,
            (world as CraftWorld).handle,
            GameProfile(UUID.randomUUID(), ""),
            ClientInformation.createDefault()
        )

        val npc: INpc = Npc(
            gameProfile = GameProfile(UUID.randomUUID(), name ?: UUID.randomUUID().toString().split("-")[0]),
            clientInformation = serverPlayer.clientInformation(),
            level = serverPlayer.level(),
            server = minecraftServer,
            id = key,
            name = name,
            location = Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0, 0.0F, 0.0F),
            playerEntity = serverPlayer,
            entityId = serverPlayer.id
        )

        callback(npc)
        return npc
    }
}