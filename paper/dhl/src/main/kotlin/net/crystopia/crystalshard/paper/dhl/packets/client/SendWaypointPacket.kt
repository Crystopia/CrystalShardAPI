package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTrackedWaypointPacketData
import net.crystopia.crystalshard.dhl.shared.data.waypoints.TrackedWaypoint
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.enums.waypoints.WaypointOperation
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder

fun ClientPacketFactory.sendWaypoint(
    operation: WaypointOperation,
    waypoints: TrackedWaypoint<*>,
    callback: (packet: Shard_Packet<ClientboundTrackedWaypointPacketData>) -> Unit
): Shard_Packet<ClientboundTrackedWaypointPacketData> {

    val data = ClientboundTrackedWaypointPacketData(
        operation, TrackedWaypoint(
            identifier = waypoints.identifier,
            icon = waypoints.icon,
            type = waypoints.type,
            data = waypoints.data
        )
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.sendWaypointPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.ClientPacketBuilder.sendWaypointPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.ClientPacketBuilder.sendWaypointPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            throw Exception("Waypoint Packets are not implemented in this Version!")
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundTrackedWaypointPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}