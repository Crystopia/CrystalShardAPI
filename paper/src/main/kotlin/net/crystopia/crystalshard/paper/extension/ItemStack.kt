package net.crystopia.crystalshard.paper.extension

import net.crystopia.crystalshard.paper.custom.CrystalEvents
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import kotlin.text.isNullOrEmpty

/**
 * Custom Interaction Method for the PlayerInteractEvent.
 * Register CrystalEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.CrystalEvents
 */
fun ItemStack.playerInteractEvent(action: PlayerInteractEvent.() -> Unit = {}) {
    val id = itemMeta.persistentDataContainer.get(
        CrystalEvents.EVENT_KEY, PersistentDataType.STRING
    )
    if (id.isNullOrEmpty()) {
        throw Exception("Item has no event interact Key!")
    } else {
        CrystalEvents.interactEvent[id] = action
    }
}

/**
 * Custom Interaction Method for the CraftItemEvent.
 * Register CrystalEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.CrystalEvents
 */
fun ItemStack.craftItemEvent(action: CraftItemEvent.() -> Unit = {}) {
    val id = itemMeta.persistentDataContainer.get(
        CrystalEvents.EVENT_KEY, PersistentDataType.STRING
    )
    if (id.isNullOrEmpty()) {
        throw Exception("Item has no event interact Key!")
    } else {
        CrystalEvents.craftItemEvent[id] = action
    }
}