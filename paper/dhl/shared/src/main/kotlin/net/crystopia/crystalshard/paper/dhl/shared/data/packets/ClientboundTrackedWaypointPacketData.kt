package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import java.util.*

data class ClientboundTrackedWaypointPacketData(
    var operation: WaypointOperation,
    var waypoints: TrackedWaypoint<*>
)

data class TrackedWaypoint<T : WaypointData>(
    var identifier: UUID,
    var icon: WaypointIcon,
    var type: WaypointType,
    var data: T
)

data class WaypointIcon(
    /**
     * eg. assets/minecraft/waypoint_style/<value>.json.
     */
    var style: String,
    var color: Int
)

open class WaypointData

data class WaypointDataVec3i(
    var x: Int,
    var y: Int,
    var z: Int
) : WaypointData()

data class WaypointDataChunk(
    var x: Int,
    var z: Int
) : WaypointData()

data class WaypointDataAzimuth(
    var angel: Float
) : WaypointData()

enum class WaypointType {
    VEC3I,
    CHUNK,
    AZIMUTH;
}

enum class WaypointOperation {
    TRACK,
    UNTRACK,
    UPDATE,
}
