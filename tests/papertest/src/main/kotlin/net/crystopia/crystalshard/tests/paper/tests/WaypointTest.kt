package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.packets.client.sendWaypoint
import net.crystopia.crystalshard.paper.dhl.shared.data.waypoints.TrackedWaypoint
import net.crystopia.crystalshard.paper.dhl.shared.data.waypoints.WaypointDataVec3i
import net.crystopia.crystalshard.paper.dhl.shared.data.waypoints.WaypointIcon
import net.crystopia.crystalshard.paper.dhl.shared.enums.waypoints.WaypointOperation
import net.crystopia.crystalshard.paper.dhl.shared.enums.waypoints.WaypointType
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class WaypointTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {

    override fun command() {
        test {
            ClientPacketFactory.sendWaypoint(
                WaypointOperation.TRACK,
                TrackedWaypoint(
                    identifier = UUID.randomUUID(),
                    icon = WaypointIcon(
                        style = "default",
                        color = 0x60008000
                    ),
                    type = WaypointType.VEC3I,
                    data = WaypointDataVec3i(
                        10, 10, 10
                    )
                )
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
        }
    }

}