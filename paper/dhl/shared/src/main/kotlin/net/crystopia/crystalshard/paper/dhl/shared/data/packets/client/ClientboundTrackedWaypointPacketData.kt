package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.waypoints.TrackedWaypoint
import net.crystopia.crystalshard.paper.dhl.shared.enums.waypoints.WaypointOperation

data class ClientboundTrackedWaypointPacketData(
    var operation: WaypointOperation, var waypoints: TrackedWaypoint<*>
)