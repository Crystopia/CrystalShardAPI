package net.crystopia.crystalshard.paper.simulacrum

import net.crystopia.crystalshard.paper.dhl.PacketFactory
import net.crystopia.crystalshard.paper.dhl.ServerUtil
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.simulacrum.types.interfaces.displays.IDisplay
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.EntityBuilder
import net.crystopia.crystalshard.paper.simulacrum.displays.PBlockDisplay
import net.crystopia.crystalshard.paper.simulacrum.displays.PItemDisplay
import net.crystopia.crystalshard.paper.simulacrum.displays.PTextDisplay
import net.crystopia.crystalshard.paper.simulacrum.npc.Npc
import net.crystopia.crystalshard.paper.simulacrum.types.interfaces.npcs.INpc
import net.minecraft.world.entity.Display
import net.minecraft.world.phys.Vec3
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

object SimulacrumFactory {

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
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.EntityBuilder.createServerPlayer(
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
        type: net.minecraft.world.entity.EntityType<*>,
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
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.EntityBuilder.createDisplayEntity(
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
            net.minecraft.world.entity.EntityType.TEXT_DISPLAY -> {
                PTextDisplay(
                    id = key, type = displayEntity.type, entity = displayEntity as Display.TextDisplay
                )
            }

            net.minecraft.world.entity.EntityType.ITEM_DISPLAY -> {

                PItemDisplay(
                    id = key, type = displayEntity.type, entity = displayEntity as Display.ItemDisplay
                )
            }

            net.minecraft.world.entity.EntityType.BLOCK_DISPLAY -> {
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