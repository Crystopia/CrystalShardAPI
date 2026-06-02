package net.crystopia.crystalshard.paper.dhl.packets.server

import net.crystopia.crystalshard.dhl.ServerPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.packets.server.MoveVehicleEvent
import net.crystopia.crystalshard.dhl.shared.ServerPacket
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.ServerNotSupported
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import org.bukkit.craftbukkit.entity.CraftPlayer

fun ServerPacketFactory.moveVehicleEvent(
    player: org.bukkit.entity.Player,
    name: org.bukkit.NamespacedKey,
    shouldPublish: Boolean,
    callback: MoveVehicleEvent.() -> Unit
) {
    val data = ServerPacket(
        (player as CraftPlayer).handle,
        NamespacedKey(name.namespace, name.key),
        shouldPublish
    )

    when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ServerPacketBuilder.moveVehicleEvent(
                data,
                callback
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ServerPacketBuilder.moveVehicleEvent(
                data,
                callback
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ServerPacketBuilder.moveVehicleEvent(
                data,
                callback
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ServerPacketBuilder.moveVehicleEvent(
                data,
                callback
            )
        }

        else -> {
            throw ServerNotSupported("${ServerUtil.currentVersion()}")
        }
    }
}