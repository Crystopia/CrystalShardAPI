package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.waypoints.TrackedWaypoint
import net.crystopia.crystalshard.dhl.shared.enums.waypoints.WaypointOperation

data class ClientboundTrackedWaypointPacketData(
    var operation: WaypointOperation, var waypoints: TrackedWaypoint<*>
)