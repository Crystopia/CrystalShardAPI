package net.crystopia.crystalshard.events

import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType


/**
 *
 * Register Crystal Events for the SmartEvent
 *
 */

object CrystalEvents : Listener {

    val key: NamespacedKey
        get() = NamespacedKey("crystalshard", "uuid")

    var interactEvent = mutableMapOf<String, PlayerInteractEvent.() -> Unit>()
    var craftItemEvent = mutableMapOf<String, CraftItemEvent.() -> Unit>()

    @EventHandler
    fun onInteractEvent(event: PlayerInteractEvent) {
        if (interactEvent.isEmpty()) {
            return
        }
        if (event.item != null && event.item!!.itemMeta.persistentDataContainer.has(key)) {
            val id = event.item!!.itemMeta.persistentDataContainer.get(
                key,
                PersistentDataType.STRING
            )
            if (event.item != null) {
                interactEvent[id]!!.invoke(event)
                return
            }
            return
        }
        return
    }

    @EventHandler
    fun onCraftItemEvent(event: CraftItemEvent) {
        if (craftItemEvent.isEmpty()) {
            return
        }
        if (event.recipe.result.persistentDataContainer.has(key, PersistentDataType.STRING)) {
            val id = event.recipe.result.itemMeta.persistentDataContainer.get(
                key,
                PersistentDataType.STRING
            )
            craftItemEvent[id]!!.invoke(event)
            return
        }
        return
    }

}