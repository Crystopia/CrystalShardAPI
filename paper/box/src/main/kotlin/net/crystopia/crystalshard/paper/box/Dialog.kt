package net.crystopia.crystalshard.paper.box

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.ServerPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.removeServerPacketListener
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.*
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.Dialog
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.ActionCustomAll
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.CustomClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

fun dialog(
    dialog: Dialog<*>,
    external: Boolean,
    plugin: JavaPlugin,
    callback: net.crystopia.crystalshard.paper.box.Dialog.() -> Unit
): net.crystopia.crystalshard.paper.box.Dialog {

    val dialog = Dialog(
        dialog = dialog,
        external = external,
        plugin = plugin,
    )
    callback.invoke(dialog)
    return dialog
}

class Dialog {

    private var customClickEvent: CustomClickEvent.() -> Unit = {}
    private var dialog: Dialog<*>
    private var listenerKey: NamespacedKey
    private var players: MutableList<Player> = mutableListOf()
    private var plugin: JavaPlugin
    private var external: Boolean

    constructor(
        dialog: Dialog<*>,
        external: Boolean,
        plugin: JavaPlugin,
    ) {

        this.dialog = dialog
        this.plugin = plugin
        this.external = external
        this.listenerKey = NamespacedKey("dialogshard", UUID.randomUUID().toString().split("-")[0])
    }

    fun listen(click: CustomClickEvent.() -> Unit) {
        this.customClickEvent = click
    }

    fun open(players: MutableList<Player>): net.crystopia.crystalshard.paper.box.Dialog {
        val dialog = this.dialog
        this.players = players
        players.forEach {
            ServerPacketFactory.customClickActionEvent(
                data = Shard_ServerPacketData(
                    player = it,
                    name = listenerKey,
                    plugin = plugin,
                    shouldPublish = external
                ),
            ) {
                handleEvent(this, dialog)
                customClickEvent.invoke(this)
            }
        }

        ClientPacketFactory.showDialog(
            this.dialog
        ) { packet ->
            packet.send(players)
        }
        return this
    }

    fun close(): net.crystopia.crystalshard.paper.box.Dialog {
        players.forEach { player ->
            player.removeServerPacketListener(listenerKey.toString())
        }
        return this
    }


    private fun handleEvent(eventData: CustomClickEvent, dialog: Dialog<*>) {
        when (dialog) {
            is ServerLinksDialog -> {
                if (dialog.exitAction?.action is ActionCustomAll) {
                    if (NamespacedKey(
                            eventData.key.namespace(),
                            eventData.key.value()
                        ) == (dialog.exitAction?.action as ActionCustomAll).id
                    ) {
                        customClickEvent.invoke(eventData)
                    }
                }
            }

            is NoticeDialog -> {
                if (dialog.action.action is ActionCustomAll) {
                    handleEventCallback(
                        eventData,
                        mutableListOf(
                            dialog.action.action as ActionCustomAll
                        )
                    )
                }
            }

            is MultiActionDialog -> {
                if (dialog.exitAction != null && dialog.exitAction?.action is ActionCustomAll) {
                    handleEventCallback(
                        eventData,
                        mutableListOf(
                            dialog.exitAction!!.action as ActionCustomAll
                        )
                    )
                }

                dialog.actions.forEach { (button, action) ->
                    if (action is ActionCustomAll) {
                        handleEventCallback(
                            eventData,
                            mutableListOf(
                                action
                            )
                        )
                    }
                }
            }

            is DialogListDialog -> {
                if (dialog.exitAction != null && dialog.exitAction?.action is ActionCustomAll) {
                    handleEventCallback(
                        eventData,
                        mutableListOf(
                            dialog.exitAction!!.action as ActionCustomAll
                        )
                    )
                }

                dialog.dialogs.forEach { dialog ->
                    handleEvent(
                        eventData = eventData,
                        dialog = dialog
                    )
                }
            }

            is ConfirmationDialog -> {
                if (dialog.yesButton.action is ActionCustomAll || dialog.noButton.action is ActionCustomAll) {
                    handleEventCallback(
                        eventData,
                        mutableListOf(
                            dialog.yesButton.action as ActionCustomAll,
                            dialog.noButton.action as ActionCustomAll
                        )
                    )
                }
            }
        }
    }

    private fun handleEventCallback(data: CustomClickEvent, buttons: MutableList<ActionCustomAll>) {
        val namespacedKey = NamespacedKey(data.key.namespace(), data.key.value())
        buttons.forEach {
            if (it.id == namespacedKey)
                this.customClickEvent.invoke(data)
        }
    }
}