package net.crystopia.crystalshard.events

import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType

object CrystalEvents : Listener {

    val key: NamespacedKey
        get() = NamespacedKey("crystalshard", "UUID")

    var interactEvent = mutableMapOf<String, PlayerInteractEvent.() -> Unit>()

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

}