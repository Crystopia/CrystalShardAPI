package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.waypoints.TrackedWaypoint
import net.crystopia.crystalshard.paper.dhl.shared.enums.waypoints.WaypointOperation
import java.util.*

data class ClientboundTrackedWaypointPacketData(
    var operation: WaypointOperation, var waypoints: TrackedWaypoint<*>
)