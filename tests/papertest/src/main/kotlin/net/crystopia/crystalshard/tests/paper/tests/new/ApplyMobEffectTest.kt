package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.entities.EffectInstance
import net.crystopia.crystalshard.dhl.shared.enums.entities.EffectType
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.applyMobEffect
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ApplyMobEffectTest : Test("ApplyMobEffectTest") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.applyMobEffect(
                entityId = player.entityId,
                effect = EffectInstance(
                    type = EffectType.SPEED,
                    duration = 200,
                    amplifier = 1,
                    ambient = false,
                    visible = true,
                    showIcon = true
                ),
                blend = false
            ) { it.send(mutableListOf(player)) }
            println("ApplyMobEffect OK")
        }
    }
}