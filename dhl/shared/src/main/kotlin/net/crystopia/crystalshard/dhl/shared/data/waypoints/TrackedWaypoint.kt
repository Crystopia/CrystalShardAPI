package net.crystopia.crystalshard.dhl.shared.data.waypoints

import net.crystopia.crystalshard.dhl.shared.enums.waypoints.WaypointType
import java.util.*

data class TrackedWaypoint<T : WaypointData>(
    var identifier: UUID,
    var icon: WaypointIcon,
    var type: WaypointType,
    var data: T
)
