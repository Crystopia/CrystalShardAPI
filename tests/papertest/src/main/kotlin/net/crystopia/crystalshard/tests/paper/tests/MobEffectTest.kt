package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.packets.client.applyMobEffect
import net.crystopia.crystalshard.dhl.shared.data.entities.EffectInstance
import net.crystopia.crystalshard.dhl.shared.enums.entities.EffectType
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MobEffectTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            ClientPacketFactory.applyMobEffect(
                (sender as Player).entityId,
                EffectInstance(
                    type = EffectType.HASTE,
                    duration = 5000,
                    amplifier = 3,
                    ambient = false,
                    visible = true,
                    showIcon = true
                ),
                true
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
        }
    }

}