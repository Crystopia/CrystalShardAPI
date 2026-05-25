package net.crystopia.crystalshard.dhl.shared.data.waypoints

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