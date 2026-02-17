package net.crystopia.crystalshard.paper.box

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.ServerPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.hasServerPacketListener
import net.crystopia.crystalshard.paper.dhl.extension.removeServerPacketListener
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.ButtonClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.ContainerClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.MenuType
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

fun gui(
    title: Component,
    type: MenuType,
    external: Boolean = false,
    plugin: JavaPlugin,
    callback: GUI.() -> Unit
): GUI {
    val gui = GUI(title, type, external, plugin)
    callback(gui)
    return gui
}

class GUI {

    val inventoryId = Math.random().toInt() * 1000000
    var title: Component
    var type: MenuType
    var external: Boolean = false
    private var items = mutableMapOf<Int, Slot>()
    private val data: MutableSet<Data> = mutableSetOf()
    private var content: Content? = null
    private var carriedItem: ItemStack? = null
    private var players: MutableList<Player> = mutableListOf()

    private var plugin: JavaPlugin
    private var clickListener: ContainerClickEvent.() -> Unit = { }
    private var buttonClickListener: ButtonClickEvent.() -> Unit = { }
    private val containerClickEventKey = NamespacedKey("$inventoryId", "containerclick")
    private val containerButtonClickEventKey = NamespacedKey("$inventoryId", "containerbuttonclick")
    private val eventData = mutableMapOf<String, GUI.(button: ButtonClickEvent?, click: ContainerClickEvent?) -> Unit>()

    constructor(title: Component, type: MenuType, external: Boolean = false, plugin: JavaPlugin) {
        this.title = title
        this.type = type
        this.plugin = plugin
        this.external = external
    }

    private fun events(player: Player): GUI {
        val gui = this
        if (player.hasServerPacketListener(containerClickEventKey.toString()))
            player.removeServerPacketListener(containerClickEventKey.toString())
        if (player.hasServerPacketListener(containerButtonClickEventKey.toString()))
            player.removeServerPacketListener(
                containerButtonClickEventKey.toString()
            )

        val itemSlots = mutableMapOf<Int, ItemStack>()
        items.forEach { (key, value) ->
            itemSlots[key] = value.item
        }
        ServerPacketFactory.containerClickEvent(
            itemSlots, Shard_ServerPacketData(
                player = player,
                name = containerClickEventKey,
                plugin = plugin,
                shouldPublish = external
            )
        ) {
            if (this.containerId == inventoryId) {
                clickListener(this)
                if (eventData.contains(this.slotNum.toInt().toString())) {
                    val data = items[this.slotNum.toInt()]
                    if (data!!.cancel) {
                        slot(
                            this.slotNum.toInt(), Slot(
                                item = data.item,
                                revision = data.revision,
                                cancel = data.cancel
                            )
                        ) { button, click -> }
                        carried(ItemStack(Material.AIR))
                        return@containerClickEvent
                    } else eventData[this.slotNum.toInt().toString()]!!.invoke(gui, null, this)
                }
            }
        }
        ServerPacketFactory.containerButtonClickEvent(
            Shard_ServerPacketData(
                player = player,
                name = containerButtonClickEventKey,
                plugin = plugin,
                shouldPublish = external
            )
        ) {
            if (this.containerId == inventoryId) {
                buttonClickListener(this)
                if (eventData.contains(this.buttonId.toString())) {
                    val data = items[this.buttonId]
                    if (data!!.cancel) {
                        slot(
                            this.containerId, Slot(
                                item = data.item,
                                revision = data.revision,
                                cancel = data.cancel
                            )
                        ) { button, click -> }
                        carried(ItemStack(Material.AIR))
                        return@containerButtonClickEvent
                    } else eventData[this.buttonId.toString()]!!.invoke(gui, this, null)
                }
            }
        }
        return this
    }

    fun players(player: Player): GUI {
        this.players.add(player)
        events(player)
        return this
    }

    fun players(players: MutableList<Player>): GUI {
        this.players = players
        players.forEach { events(it) }
        return this
    }

    fun slots(items: MutableMap<Int, Slot>): GUI {
        this.items = items
        update(players)
        return this
    }

    fun content(content: Content): GUI {
        this.content = content
        update(players)
        return this
    }

    fun data(data: Data): GUI {
        this.data.add(data)
        update(players)
        return this
    }

    fun slot(slot: Int, data: Slot, event: GUI.(button: ButtonClickEvent?, click: ContainerClickEvent?) -> Unit): GUI {
        ClientPacketFactory.setContainerSlot(
            inventoryId,
            data.revision,
            slot,
            data.item
        ) { packet ->
            packet.send(players)
        }
        this.eventData[slot.toString()] = event
        this.items[slot] = data
        update(players)
        return this
    }

    fun slot(
        slot: MutableList<Int>,
        data: Slot,
        event: GUI.(button: ButtonClickEvent?, click: ContainerClickEvent?) -> Unit
    ): GUI {
        slot.forEach { slot ->
            ClientPacketFactory.setContainerSlot(
                inventoryId,
                data.revision,
                slot,
                data.item
            ) { packet ->
                packet.send(players)
            }
            this.eventData[slot.toString()] = event
            this.items[slot] = data
        }
        update(players)
        return this
    }

    fun close(player: Player): GUI {
        ClientPacketFactory.closeContainerPacket(inventoryId) { packet ->
            packet.send(mutableListOf(player))
        }
        player.removeServerPacketListener(
            containerButtonClickEventKey.toString()
        )
        player.removeServerPacketListener(
            containerButtonClickEventKey.toString()
        )
        return this
    }

    fun close(): GUI {
        players.forEach {
            ClientPacketFactory.closeContainerPacket(inventoryId) { packet ->
                packet.send(mutableListOf(it))
            }
            it.removeServerPacketListener(
                containerButtonClickEventKey.toString()
            )
            it.removeServerPacketListener(
                containerButtonClickEventKey.toString()
            )
        }
        return this
    }

    fun carried(item: ItemStack) {
        this.carriedItem = item
        ClientPacketFactory.setItemOnCursor(
            carriedItem!!,
            1000
        ) { packet ->
            packet.send(players)
        }
        update(players)
    }

    fun update(players: MutableList<Player>): GUI {

        ClientPacketFactory.setContainerContent(
            inventoryId, content?.stateId ?: 0,
            items.toSortedMap().map { it.value.item }.toMutableList(),
            carriedItem
        ) { packet ->
            packet.send(players)
        }

        data.forEach {
            ClientPacketFactory.setContainerData(inventoryId, it.property, it.value) { packet ->
                packet.send(players)
            }
        }
        return this
    }

    fun open(player: Player): GUI {
        ClientPacketFactory.openScreenPacket(inventoryId, title, type) { packet ->
            packet.send(mutableListOf(player))
        }
        return this
    }

    fun open(): GUI {
        ClientPacketFactory.openScreenPacket(inventoryId, title, type) { packet ->
            packet.send(players)
        }
        return this
    }

    fun buttonClick(callback: ButtonClickEvent.() -> Unit): GUI {
        buttonClickListener = callback
        return this
    }

    fun click(callback: ContainerClickEvent.() -> Unit): GUI {
        clickListener = callback
        return this
    }

    data class Slot(
        var item: ItemStack,
        var revision: Int,
        var cancel: Boolean = false
    )

    data class Data(
        var property: Short,
        var value: Short
    )

    data class Content(
        var stateId: Int,
    )

}