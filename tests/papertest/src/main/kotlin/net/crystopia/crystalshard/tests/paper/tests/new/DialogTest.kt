package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.dialog.ActionType
import net.crystopia.crystalshard.dhl.shared.enums.dialog.DialogAction
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.showDialog
import net.crystopia.crystalshard.paper.dhl.types.dialog.CommonDialogData
import net.crystopia.crystalshard.paper.dhl.types.dialog.ConfirmationDialog
import net.crystopia.crystalshard.paper.dhl.types.dialog.DialogBodyPlainMessage
import net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionButton
import net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll
import net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction
import net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.CommonButtonData
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class DialogTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {

            ClientPacketFactory.showDialog(
                ConfirmationDialog(
                    common = CommonDialogData(
                        title = Component.text("PACKET DIALOG"),
                        externalTitle = Component.text("PACKET DIALOG"),
                        canCloseWithEscape = false,
                        pause = false,
                        afterAction = DialogAction.CLOSE,
                        body = mutableListOf(
                            DialogBodyPlainMessage(
                                contents = Component.text("Hey this is a dialog only for you!"),
                                width = 100
                            )
                        ),
                        inputs = mutableListOf()
                    ),
                    yesButton = ActionButton(
                        button = CommonButtonData(
                            label = Component.text("Click me for more packets :)"),
                            tooltip = null,
                            width = 500
                        ),
                        action = ActionCustomAll(
                            id = NamespacedKey("testy", "packet"),
                            additionsNBT = mutableMapOf(Pair("test", "1234"))
                        )
                    ),
                    noButton = ActionButton(
                        button = CommonButtonData(
                            label = Component.text("No Packets... :("),
                            tooltip = null,
                            width = 100
                        ),
                        action = ActionStaticAction(
                            type = ActionType.URL,
                            value = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
                        )
                    )
                )
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
        }
    }

}