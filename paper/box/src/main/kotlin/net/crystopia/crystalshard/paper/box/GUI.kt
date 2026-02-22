package net.crystopia.crystalshard.paper.box

import me.lucko.spark.paper.lib.protobuf.ExperimentalApi
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.ServerPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.hasServerPacketListener
import net.crystopia.crystalshard.paper.dhl.extension.removeServerPacketListener
import net.crystopia.crystalshard.paper.dhl.shared.data.gui.Slot
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

@ExperimentalApi("stable release of the packetGuis... (stable version)")
fun gui(
    inventoryId: Int,
    title: Component,
    type: MenuType,
    shouldPublish: Boolean = false,
    plugin: JavaPlugin,
    callback: GUI.() -> Unit
): GUI {
    val gui = GUI(inventoryId, title, type, shouldPublish, plugin)
    callback(gui)
    return gui
}

@ExperimentalApi("stable release of the packetGuis... (stable version)")
class GUI {

    private var inventoryId: Int? = null
    private var title: Component
    private var type: MenuType
    private var shouldPublish: Boolean = false
    private val items = mutableMapOf<Int, Data.Slot>()
    private val data: MutableSet<Data.Data> = mutableSetOf()
    private var content: Data.Content? = null
    private var carriedItem: ItemStack? = null
    private var players: MutableList<Player> = mutableListOf()

    private var plugin: JavaPlugin
    private var clickListener: ContainerClickEvent.() -> Unit = { }
    private var buttonClickListener: ButtonClickEvent.() -> Unit = { }
    private val containerClickEventKey = NamespacedKey("$inventoryId", "containerclick")
    private val containerButtonClickEventKey = NamespacedKey("$inventoryId", "containerbuttonclick")
    private val eventData = mutableMapOf<String, GUI.(button: ButtonClickEvent?, click: ContainerClickEvent?) -> Unit>()

    constructor(inventoryId: Int, title: Component, type: MenuType, external: Boolean = false, plugin: JavaPlugin) {
        this.inventoryId = inventoryId
        this.title = title
        this.type = type
        this.plugin = plugin
        this.shouldPublish = external
    }

    private fun events(player: Player): GUI {
        val gui = this
        val items = this.items
        if (player.hasServerPacketListener(containerClickEventKey.toString()))
            player.removeServerPacketListener(containerClickEventKey.toString())
        if (player.hasServerPacketListener(containerButtonClickEventKey.toString()))
            player.removeServerPacketListener(
                containerButtonClickEventKey.toString()
            )

        ServerPacketFactory.containerClickEvent(
            this.items.map {
                Slot(
                    id = it.value.slot,
                    item = it.value.item
                )
            }.toMutableList(),
            Shard_ServerPacketData(
                player = player,
                name = containerClickEventKey,
                plugin = plugin,
                shouldPublish = shouldPublish
            )
        ) {
            // CONTAINER CLICK
            if (this.containerId == inventoryId) {
                clickListener(this)
                if (eventData.contains(this.slotNum.toInt().toString())) {
                    eventData[this.slotNum.toInt().toString()]!!.invoke(gui, null, this)

                    val data = items[this.slotNum.toInt()]
                    if (data?.cancel == true) {
                        carried(ItemStack(Material.AIR))
                    }
                }
            }
        }
        ServerPacketFactory.containerButtonClickEvent(
            Shard_ServerPacketData(
                player = player,
                name = containerButtonClickEventKey,
                plugin = plugin,
                shouldPublish = shouldPublish
            )
        ) {
            // BUTTON CLICK
            if (this.containerId == inventoryId) {
                buttonClickListener(this)
                if (eventData.contains(this.buttonId.toString())) {
                    eventData[this.buttonId.toString()]!!.invoke(gui, this, null)

                    val data = items[this.buttonId]
                    if (data?.cancel == true) {
                        carried(ItemStack(Material.AIR))
                    }
                }
            }
        }

        return this
    }

    fun player(player: Player): GUI {
        this.players.add(player)
        return this
    }

    fun players(players: MutableList<Player>): GUI {
        this.players = players
        players.forEach { events(it) }
        return this
    }

    fun content(content: Data.Content): GUI {
        this.content = content
        return this
    }

    fun data(data: Data.Data): GUI {
        this.data.add(data)
        return this
    }

    fun slot(data: Data.Slot, event: GUI.(button: ButtonClickEvent?, click: ContainerClickEvent?) -> Unit): GUI {
        ClientPacketFactory.setContainerSlot(
            inventoryId!!,
            data.revision,
            data.slot,
            data.item
        ) { packet ->
            packet.send(players)
        }
        this.eventData[data.slot.toString()] = event
        this.items[data.slot] = (data)

        // Refresh user event listeners
        this.players.forEach {
            events(it)
        }

        return this
    }

    fun close(player: Player): GUI {
        ClientPacketFactory.closeContainerPacket(inventoryId!!) { packet ->
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
            ClientPacketFactory.closeContainerPacket(inventoryId!!) { packet ->
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
        update()
    }

    fun update(): GUI {
        ClientPacketFactory.setContainerContent(
            inventoryId!!,
            10000,
            mutableMapOf(),
            carriedItem
        ) { packet ->
            packet.send(players)
        }

        // TODO: Change this only to Container Content...
        this.items.forEach { slot ->
            ClientPacketFactory.setContainerSlot(
                inventoryId!!,
                slot.value.revision,
                slot.value.slot,
                slot.value.item
            ) { packet ->
                packet.send(players)
            }
        }

        data.forEach {
            ClientPacketFactory.setContainerData(inventoryId!!, it.property, it.value) { packet ->
                packet.send(players)
            }
        }

        this.players.forEach { events(it) }
        return this
    }

    fun open(player: Player): GUI {
        ClientPacketFactory.openScreenPacket(inventoryId!!, title, type) { packet ->
            packet.send(mutableListOf(player))
        }
        update()
        return this
    }

    fun open(): GUI {
        ClientPacketFactory.openScreenPacket(inventoryId!!, title, type) { packet ->
            packet.send(players)
        }
        update()
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

    // DATA start

    class Data {
        data class Slot(
            var slot: Int,
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
}