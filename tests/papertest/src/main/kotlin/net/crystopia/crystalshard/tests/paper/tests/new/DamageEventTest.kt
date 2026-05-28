package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.damageEvent
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class DamageEventTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            val damageSource = org.bukkit.damage.DamageSource.builder(
                org.bukkit.damage.DamageType.ON_FIRE
            ).build()
            ClientPacketFactory.damageEvent(
                entity = player,
                damageSource = damageSource
            ) { it.send(mutableListOf(player)) }
            println("DamageEvent OK")
        }
    }
}