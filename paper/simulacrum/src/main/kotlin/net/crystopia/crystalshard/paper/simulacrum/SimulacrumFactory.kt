package net.crystopia.crystalshard.paper.simulacrum

import net.crystopia.crystalshard.paper.dhl.PacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.EntityBuilder
import net.crystopia.crystalshard.paper.simulacrum.displays.PBlockDisplay
import net.crystopia.crystalshard.paper.simulacrum.displays.PItemDisplay
import net.crystopia.crystalshard.paper.simulacrum.displays.PTextDisplay
import net.crystopia.crystalshard.paper.simulacrum.npc.Npc
import net.crystopia.crystalshard.paper.simulacrum.types.interfaces.displays.IDisplay
import net.crystopia.crystalshard.paper.simulacrum.types.interfaces.npcs.INpc
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.block.CraftBlockType
import org.bukkit.craftbukkit.entity.CraftEntityType
import org.bukkit.craftbukkit.entity.CraftEntityTypes
import org.bukkit.entity.*

object SimulacrumFactory {

    fun <T : Entity> createEntityInstance(
        type: EntityType, location: Location, callback: T.() -> Unit
    ): T {

        val instance = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                EntityBuilder.createEntityInstance(
                    type, location
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.EntityBuilder.createEntityInstance(
                    type, location
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        callback(instance.bukkitEntity as T)
        return instance.bukkitEntity as T
    }

    fun <T : INpc> createNpc(
        location: Location, key: NamespacedKey, name: String, callback: T.() -> Unit
    ): INpc {

        val serverplayer = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                EntityBuilder.createServerPlayer(
                    location.world, name
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.EntityBuilder.createServerPlayer(
                    location.world, name
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val npc: INpc = Npc(
            gameProfile = serverplayer.gameProfile,
            id = key,
            name = name,
            location = location,
            playerEntity = serverplayer.bukkitEntity,
            entityId = serverplayer.id,
        )

        callback(npc as T)
        return npc
    }

    fun <T : IDisplay<*>> createDisPlayEntity(
        key: NamespacedKey,
        type: org.bukkit.entity.EntityType,
        location: Location,
        players: MutableList<Player>,
        callback: T.() -> Unit
    ): IDisplay<*> {

        val displayEntity = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                EntityBuilder.createDisplayEntity(
                    CraftEntityType.bukkitToMinecraft(type), location
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.EntityBuilder.createDisplayEntity(
                    CraftEntityType.bukkitToMinecraft(type), location
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        PacketFactory.addEntitiesPacket(
            displayEntity!!.id, displayEntity.uuid, location,
            entityType = type,
            data = 0,
            yHeadRot = 0.0,
        ) { packet ->
            packet.send(players)
        }


        val obj = when (displayEntity.type) {
            net.minecraft.world.entity.EntityType.TEXT_DISPLAY -> {
                PTextDisplay(
                    id = key, type = type, entity = displayEntity.bukkitEntity as TextDisplay
                )
            }

            net.minecraft.world.entity.EntityType.ITEM_DISPLAY -> {

                PItemDisplay(
                    id = key, type = type, entity = displayEntity.bukkitEntity as ItemDisplay
                )
            }

            net.minecraft.world.entity.EntityType.BLOCK_DISPLAY -> {
                PBlockDisplay(
                    id = key, type = type, entity = displayEntity.bukkitEntity as BlockDisplay
                )
            }

            else -> throw IllegalArgumentException("Failed to create Display Object.")
        }

        callback(obj as T)
        return obj
    }
}