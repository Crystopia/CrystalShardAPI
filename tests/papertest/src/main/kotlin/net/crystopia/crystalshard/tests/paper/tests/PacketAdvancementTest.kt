package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.custom.advancements.advancement
import net.crystopia.crystalshard.paper.custom.advancements.models.AdvancementModel
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.PacketFactory
import net.crystopia.crystalshard.paper.dhl.packets.client.initWorldBorder
import net.crystopia.crystalshard.paper.dhl.packets.client.setWorldBorderLerpSize
import net.crystopia.crystalshard.paper.dhl.packets.client.updateAdvancements
import net.crystopia.crystalshard.paper.dhl.shared.data.world.WorldBorder
import net.crystopia.crystalshard.paper.dhl.shared.enums.player.GameMode
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
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