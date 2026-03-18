package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.CommonDialogData
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.ConfirmationDialog
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.DialogBodyPlainMessage
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.ActionButton
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.ActionCustomAll
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.ActionStaticAction
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.CommonButtonData
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EffectInstance
import net.crystopia.crystalshard.paper.dhl.shared.enums.dialog.DialogAction
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EffectType
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import org.bukkit.NamespacedKey
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