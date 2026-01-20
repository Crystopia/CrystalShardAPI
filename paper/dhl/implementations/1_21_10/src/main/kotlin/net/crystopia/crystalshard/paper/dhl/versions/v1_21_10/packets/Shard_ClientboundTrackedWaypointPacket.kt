package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.*
import net.crystopia.crystalshard.paper.dhl.shared.data.waypoints.WaypointDataAzimuth
import net.crystopia.crystalshard.paper.dhl.shared.data.waypoints.WaypointDataChunk
import net.crystopia.crystalshard.paper.dhl.shared.data.waypoints.WaypointDataVec3i
import net.crystopia.crystalshard.paper.dhl.shared.enums.waypoints.WaypointOperation
import net.crystopia.crystalshard.paper.dhl.shared.enums.waypoints.WaypointType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.Vec3i
import net.minecraft.network.protocol.game.ClientboundTrackedWaypointPacket
import net.minecraft.world.level.ChunkPos
import net.minecraft.world.waypoints.TrackedWaypoint
import net.minecraft.world.waypoints.Waypoint
import net.minecraft.world.waypoints.WaypointStyleAssets

class Shard_ClientboundTrackedWaypointPacket : IPacket<ClientboundTrackedWaypointPacketData> {

    override fun createPacket(
        packetObj: ClientboundTrackedWaypointPacketData
    ): ClientboundTrackedWaypointPacket {

        val waypoint = packetObj.waypoints

        return when (packetObj.operation) {
            WaypointOperation.TRACK -> {
                when (waypoint.type) {
                    WaypointType.AZIMUTH -> {
                        val azimuth = TrackedWaypoint.setAzimuth(
                            waypoint.identifier,
                            Waypoint.Icon(),
                            (waypoint.data as WaypointDataAzimuth).angel
                        )
                        azimuth.icon().style =
                            WaypointStyleAssets.createId(waypoint.icon.style)
                        azimuth.icon().color = java.util.Optional.of(waypoint.icon.color)

                        val waypointPacket = ClientboundTrackedWaypointPacket.addWaypointAzimuth(
                            waypoint.identifier,
                            Waypoint.Icon(),
                            (waypoint.data as WaypointDataAzimuth).angel
                        )

                        ClientboundTrackedWaypointPacket(
                            waypointPacket.operation,
                            azimuth
                        )
                    }

                    WaypointType.CHUNK -> {

                        val chunk = TrackedWaypoint.setChunk(
                            waypoint.identifier,
                            Waypoint.Icon(),
                            ChunkPos((waypoint.data as WaypointDataChunk).x, (waypoint.data as WaypointDataChunk).z)
                        )
                        chunk.icon().style =
                            WaypointStyleAssets.createId(waypoint.icon.style)
                        chunk.icon().color = java.util.Optional.of(waypoint.icon.color)

                        val waypointPacket = ClientboundTrackedWaypointPacket.addWaypointChunk(
                            waypoint.identifier,
                            Waypoint.Icon(),
                            ChunkPos((waypoint.data as WaypointDataChunk).x, (waypoint.data as WaypointDataChunk).z)
                        )

                        ClientboundTrackedWaypointPacket(
                            waypointPacket.operation,
                            chunk
                        )
                    }

                    WaypointType.VEC3I -> {
                        val vec3i = TrackedWaypoint.setPosition(
                            waypoint.identifier,
                            Waypoint.Icon(),
                            Vec3i(
                                (waypoint.data as WaypointDataVec3i).x, (waypoint.data as WaypointDataVec3i).y, (waypoint.data as WaypointDataVec3i).z
                            )
                        )
                        vec3i.icon().style =
                            WaypointStyleAssets.createId(waypoint.icon.style)
                        vec3i.icon().color = java.util.Optional.of(waypoint.icon.color)

                        val waypointPacket = ClientboundTrackedWaypointPacket.addWaypointPosition(
                            waypoint.identifier,
                            Waypoint.Icon(),
                            Vec3i(
                                (waypoint.data as WaypointDataVec3i).x, (waypoint.data as WaypointDataVec3i).y, (waypoint.data as WaypointDataVec3i).z
                            )
                        )

                        ClientboundTrackedWaypointPacket(
                            waypointPacket.operation,
                            vec3i
                        )
                    }
                }
            }

            WaypointOperation.UPDATE -> {

                when (waypoint.type) {
                    WaypointType.AZIMUTH -> {

                        val azimuth = TrackedWaypoint.setAzimuth(waypoint.identifier, Waypoint.Icon(), (waypoint.data as WaypointDataAzimuth).angel)
                        azimuth.icon().style =
                            WaypointStyleAssets.createId(waypoint.icon.style)
                        azimuth.icon().color = java.util.Optional.of(waypoint.icon.color)

                        val waypointPacket = ClientboundTrackedWaypointPacket.updateWaypointAzimuth(
                            waypoint.identifier,
                            Waypoint.Icon(),
                            (waypoint.data as WaypointDataAzimuth).angel
                        )

                        ClientboundTrackedWaypointPacket(
                            waypointPacket.operation,
                            azimuth
                        )
                    }

                    WaypointType.CHUNK -> {

                        val chunk = TrackedWaypoint.setChunk(
                            waypoint.identifier,
                            Waypoint.Icon(),
                            ChunkPos((waypoint.data as WaypointDataChunk).x, (waypoint.data as WaypointDataChunk).z)
                        )
                        chunk.icon().style =
                            WaypointStyleAssets.createId(waypoint.icon.style)
                        chunk.icon().color = java.util.Optional.of(waypoint.icon.color)

                        val waypointPacket = ClientboundTrackedWaypointPacket.updateWaypointChunk(
                            waypoint.identifier,
                            Waypoint.Icon(),
                            ChunkPos((waypoint.data as WaypointDataChunk).x, (waypoint.data as WaypointDataChunk).z)
                        )

                        ClientboundTrackedWaypointPacket(
                            waypointPacket.operation,
                            chunk
                        )
                    }

                    WaypointType.VEC3I -> {

                        val vec3i = TrackedWaypoint.setPosition(
                            waypoint.identifier,
                            Waypoint.Icon(),
                            Vec3i(
                                (waypoint.data as WaypointDataVec3i).x, (waypoint.data as WaypointDataVec3i).y, (waypoint.data as WaypointDataVec3i).z
                            )
                        )
                        vec3i.icon().style =
                            WaypointStyleAssets.createId(waypoint.icon.style)
                        vec3i.icon().color = java.util.Optional.of(waypoint.icon.color)

                        val waypointPacket = ClientboundTrackedWaypointPacket.updateWaypointPosition(
                            waypoint.identifier,
                            Waypoint.Icon(),
                            Vec3i(
                                (waypoint.data as WaypointDataVec3i).x, (waypoint.data as WaypointDataVec3i).y, (waypoint.data as WaypointDataVec3i).z
                            )
                        )

                        ClientboundTrackedWaypointPacket(
                            waypointPacket.operation,
                            vec3i
                        )
                    }
                }
            }

            WaypointOperation.UNTRACK -> {
                ClientboundTrackedWaypointPacket.removeWaypoint(waypoint.identifier)
            }
        }
    }
}