package net.crystopia.crystalshard.paper.box

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.ServerPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.removeServerPacketListener
import net.crystopia.crystalshard.paper.dhl.packets.client.showDialog
import net.crystopia.crystalshard.paper.dhl.packets.server.customClickActionEvent
import net.crystopia.crystalshard.dhl.shared.data.dialog.*
import net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionCustomAll
import net.crystopia.crystalshard.dhl.shared.data.packets.server.CustomClickEvent
import net.crystopia.crystalshard.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.types.dialog.ConfirmationDialog
import net.crystopia.crystalshard.paper.dhl.types.dialog.DialogListDialog
import net.crystopia.crystalshard.paper.dhl.types.dialog.MultiActionDialog
import net.crystopia.crystalshard.paper.dhl.types.dialog.NoticeDialog
import net.crystopia.crystalshard.paper.dhl.types.dialog.ServerLinksDialog
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

fun packetDialog(
    dialog: net.crystopia.crystalshard.paper.dhl.types.dialog.Dialog<*>,
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
    private var dialog: net.crystopia.crystalshard.paper.dhl.types.dialog.Dialog<*>
    private var listenerKey: NamespacedKey
    private var players: MutableList<Player> = mutableListOf()
    private var plugin: JavaPlugin
    private var external: Boolean

    constructor(
        dialog: net.crystopia.crystalshard.paper.dhl.types.dialog.Dialog<*>,
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
                it, listenerKey, external
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


    private fun handleEvent(eventData: CustomClickEvent, dialog: net.crystopia.crystalshard.paper.dhl.types.dialog.Dialog<*>) {
        when (dialog) {
            is ServerLinksDialog -> {
                if (dialog.exitAction?.action is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll) {
                    if (NamespacedKey(
                            eventData.key.namespace,
                            eventData.key.key
                        ) == (dialog.exitAction?.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll).id
                    ) {
                        customClickEvent.invoke(eventData)
                    }
                }
            }

            is NoticeDialog -> {
                if (dialog.action.action is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll) {
                    handleEventCallback(
                        eventData,
                        mutableListOf(
                            dialog.action.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll
                        )
                    )
                }
            }

            is MultiActionDialog -> {
                if (dialog.exitAction.action is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll) {
                    handleEventCallback(
                        eventData,
                        mutableListOf(
                            dialog.exitAction.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll
                        )
                    )
                }

                dialog.actions.forEach { (button, action) ->
                    if (action is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll) {
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
                if (dialog.exitAction != null && dialog.exitAction?.action is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll) {
                    handleEventCallback(
                        eventData,
                        mutableListOf(
                            dialog.exitAction!!.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll
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
                if (dialog.yesButton.action is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll || dialog.noButton.action is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll) {
                    handleEventCallback(
                        eventData,
                        mutableListOf(
                            dialog.yesButton.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll,
                            dialog.noButton.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll
                        )
                    )
                }
            }
        }
    }

    private fun handleEventCallback(data: CustomClickEvent, buttons: MutableList<net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll>) {
        val namespacedKey = NamespacedKey(data.key.namespace, data.key.key)
        buttons.forEach {
            if (it.id == namespacedKey)
                this.customClickEvent.invoke(data)
        }
    }
}