package net.crystopia.crystalshard.paper.dhl.shared.data.waypoints

import net.crystopia.crystalshard.paper.dhl.shared.enums.waypoints.WaypointType
import java.util.UUID

data class TrackedWaypoint<T : WaypointData>(
    var identifier: UUID,
    var icon: WaypointIcon,
    var type: WaypointType,
    var data: T
)
