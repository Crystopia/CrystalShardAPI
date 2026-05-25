package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.box.packetDialog
import net.crystopia.crystalshard.dhl.shared.data.dialog.CommonDialogData
import net.crystopia.crystalshard.dhl.shared.data.dialog.*
import net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage
import net.crystopia.crystalshard.dhl.shared.data.dialog.DialogListDialog
import net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionButton
import net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionCustomAll
import net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.CommonButtonData
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInput
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInputMultilineOptions
import net.crystopia.crystalshard.dhl.shared.enums.dialog.DialogAction
import net.crystopia.crystalshard.tests.paper.CrystalShardPluginTest
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PacketDialogTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            packetDialog(
                net.crystopia.crystalshard.paper.dhl.types.dialog.DialogListDialog(
                    net.crystopia.crystalshard.paper.dhl.types.dialog.CommonDialogData(
                        title = Component.text().text("<green>MANY DIALOGS<green>").build(),
                        canCloseWithEscape = false,
                        pause = false,
                        afterAction = DialogAction.CLOSE,
                        body = mutableListOf(
                            net.crystopia.crystalshard.paper.dhl.types.dialog.DialogBodyPlainMessage(
                                contents = Component.text().text("<gray>WIESO SO VIELE!</gray>").build(),
                                width = 500
                            )
                        ),
                        externalTitle = null,
                        inputs = mutableListOf()
                    ),
                    mutableSetOf(
                        net.crystopia.crystalshard.paper.dhl.types.dialog.ConfirmationDialog(
                            common = net.crystopia.crystalshard.paper.dhl.types.dialog.CommonDialogData(
                                title = Component.text().text("<green>Magst du gerne eis?<green>").build(),
                                canCloseWithEscape = false,
                                pause = false,
                                afterAction = DialogAction.CLOSE,
                                body = mutableListOf(
                                    net.crystopia.crystalshard.paper.dhl.types.dialog.DialogBodyPlainMessage(
                                        contents = Component.text().text("<gray>Bitte wähle aus!</gray>").build(),
                                        width = 500
                                    )
                                ),
                                externalTitle = null,
                                inputs = mutableListOf(
                                    net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogTextInput(
                                        id = "ice",
                                        width = 100,
                                        label = Component.text("Ice"),
                                        labelVisible = true,
                                        initial = "VANILLE",
                                        maxLength = 100000,
                                        multiline = net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogTextInputMultilineOptions(
                                            maxLines = 1,
                                            height = 100
                                        )
                                    )
                                )
                            ),
                            yesButton = net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionButton(
                                button = net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.CommonButtonData(
                                    label = Component.text().text("Ja ich liebe es!").build(),
                                    tooltip = null,
                                    width = 100
                                ),
                                action = net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll(
                                    id = NamespacedKey("eis", "yes"),
                                    additionsNBT = mutableMapOf()
                                )
                            ),
                            noButton = net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionButton(
                                button = net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.CommonButtonData(
                                    label = Component.text().text("Nein ich hasse eis....").build(),
                                    tooltip = null,
                                    width = 100
                                ),
                                action = net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll(
                                    id = NamespacedKey("eis", "no"),
                                    additionsNBT = mutableMapOf(Pair("abc", "11"))
                                )
                            )
                        )
                    ),
                    net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionButton(
                        button = net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.CommonButtonData(
                            label = Component.text().text("CLOSE").build(),
                            tooltip = null,
                            width = 100
                        ),
                        action = net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll(
                            id = NamespacedKey("close", "none"),
                            additionsNBT = mutableMapOf(Pair("close", "yes"))
                        )
                    ),
                    1,
                    100
                ),

                false,
                CrystalShardPluginTest.instance
            ) {
                listen {
                    println(this.payload.type.data)
                }
                open(mutableListOf(sender as Player))
            }
        }
    }

}