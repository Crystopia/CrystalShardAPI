package net.crystopia.crystalshard.extras.factories

import net.crystopia.crystalshard.paper.utils.ServerUtil
import net.crystopia.crystalshard.extras.displays.PBlockDisplay
import net.crystopia.crystalshard.extras.displays.PItemDisplay
import net.crystopia.crystalshard.extras.displays.PTextDisplay
import net.crystopia.crystalshard.extras.npc.Npc
import net.crystopia.crystalshard.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.shared.interfaces.displays.IDisplay
import net.crystopia.crystalshard.shared.interfaces.npcs.INpc
import net.crystopia.crystalshard.versions.v1_21_10.general.EntityBuilder
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import net.minecraft.world.phys.Vec3
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player

object EntityFactory {

    fun <T : INpc> createNpc(
        location: Location, key: NamespacedKey, name: String, callback: T.() -> Unit
    ): INpc {

        val serverplayer = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                EntityBuilder.createServerPlayer(
                    location.world, key, name
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.versions.v1_21_1.general.EntityBuilder.createServerPlayer(
                    location.world, key, name
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
            playerEntity = serverplayer,
            entityId = serverplayer.id,
        )

        callback(npc as T)
        return npc
    }

    fun <T : IDisplay> createDisPlayEntity(
        key: NamespacedKey,
        type: EntityType<*>,
        location: Location,
        players: MutableList<Player>,
        callback: T.() -> Unit
    ): IDisplay {

        val displayEntity = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                EntityBuilder.createDisplayEntity(
                    type, location
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.versions.v1_21_1.general.EntityBuilder.createDisplayEntity(
                    type, location
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        PacketFactory.addEntitiesPacket(
            displayEntity!!.id, displayEntity.uuid, location,
            entityType = displayEntity.type,
            data = 0,
            deltaMovement = Vec3.ZERO,
            yHeadRot = 0.0,
        ) { packet ->
            PacketFactory.sendPacket(packet, players)
        }


        val obj = when (displayEntity.type) {
            EntityType.TEXT_DISPLAY -> {
                PTextDisplay(
                    id = key, type = displayEntity.type, entity = displayEntity as Display.TextDisplay
                )
            }

            EntityType.ITEM_DISPLAY -> {

                PItemDisplay(
                    id = key, type = displayEntity.type, entity = displayEntity as Display.ItemDisplay
                )
            }

            EntityType.BLOCK_DISPLAY -> {
                PBlockDisplay(
                    id = key, type = displayEntity.type, entity = displayEntity as Display.BlockDisplay
                )
            }

            else -> throw IllegalArgumentException("Failed to create Display Object.")
        }

        callback(obj as T)
        return obj
    }
}