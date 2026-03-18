package net.crystopia.crystalshard.paper.custom.gui

import net.crystopia.crystalshard.paper.custom.extension.onClick
import net.crystopia.crystalshard.paper.custom.extension.onClose
import net.crystopia.crystalshard.paper.custom.extension.onSlotClick
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack

/**
 * **Credits to https://github.com/flytegg/twilight/blob/master/src/main/kotlin/gg/flyte/twilight/gui/GUI.kt**
 */
inline fun customGUI(
    title: Component = Component.text("Custom GUI"),
    size: Int = 27,
    type: InventoryType = InventoryType.CHEST,
    noinline context: CustomGUI.() -> Unit
): CustomGUI {
    return CustomGUI(title, size, type, context)
}

class CustomGUI(val title: Component, val size: Int, val type: InventoryType, val context: CustomGUI.() -> Unit) {

    private val inventory = when (type) {
        InventoryType.CHEST -> Bukkit.createInventory(null, size, title)
        else -> Bukkit.createInventory(null, type, title)
    }

    private val keySlot = mutableMapOf<Char, MutableList<Int>>()

    lateinit var viewer: Player

    fun pattern(vararg pattern: String) {
        for ((index, value) in pattern.joinToString("").withIndex()) {
            keySlot.getOrPut(value) { mutableListOf() }.add(index)
        }
    }

    /**
     * Set the action to be executed when the player clicks on any slot while the GUI is open.
     */
    fun onClick(action: InventoryClickEvent.() -> Unit) {
        inventory.onClick(action)
    }

    /**
     * Set the action to be executed when the inventory is closed
     */
    fun onClose(action: InventoryCloseEvent.() -> Unit) {
        inventory.onClose(action)
    }

    @JvmName("setSlot")
    fun set(slot: Int, item: ItemStack, action: InventoryClickEvent.() -> Unit = {}) {
        inventory.setItem(slot, item)
        inventory.onSlotClick(slot, action)
    }

    @JvmName("setSlots")
    fun set(indexes: Collection<Int>, item: ItemStack, action: InventoryClickEvent.() -> Unit = {}) {
        indexes.forEach { set(it, item, action) }
    }

    @JvmName("setKey")
    fun set(key: Char, item: ItemStack, action: InventoryClickEvent.() -> Unit = {}) {
        keySlot[key]?.forEach { set(it, item, action) }
    }

    @JvmName("setKeys")
    fun set(keys: Collection<Char>, item: ItemStack, action: InventoryClickEvent.() -> Unit = {}) {
        keys.forEach { set(it, item, action) }
    }

    companion object {
        fun Player.openInventory(gui: CustomGUI) {
            gui.viewer = this
            gui.context.invoke(gui)
            openInventory(gui.inventory)
        }
    }

}