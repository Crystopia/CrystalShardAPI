package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.PacketFactory
import net.crystopia.crystalshard.paper.custom.advancements.advancement
import net.crystopia.crystalshard.paper.custom.advancements.models.AdvancementModel
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.updateAdvancements
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PacketAdvancementTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {

            PacketFactory.client.updateAdvancements(
                false, mutableListOf(
                    advancement(NamespacedKey("test", "test_packet")) {
                        advancementData = AdvancementModel(
                            parent = TODO(),
                            display = TODO(),
                            criteria = TODO(),
                            requirements = TODO(),
                            rewards = TODO(),
                            sends_telemetry_event = TODO()
                        )
                    }.advancement!!
                ), mutableSetOf(), mutableMapOf(), false
            ) { it.send(mutableListOf(sender as Player)) }

        }
    }

}