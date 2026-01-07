package net.crystopia.crystalshard.paper.custom

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType
import kotlin.collections.get


/**
 *
 * Register Crystal Events for the SmartEvent
 *
 */

object CrystalEvents : Listener {

    val EVENT_KEY: NamespacedKey
        get() = NamespacedKey("crystalshardevents", "uuid")

    var interactEvent = mutableMapOf<String, PlayerInteractEvent.() -> Unit>()
    var craftItemEvent = mutableMapOf<String, CraftItemEvent.() -> Unit>()
    var displayEvents = mutableMapOf<String, PlayerInteractEntityEvent.() -> Unit>()
    var advancementCriterionGrantEvent = mutableMapOf<NamespacedKey, PlayerAdvancementCriterionGrantEvent.() -> Unit>()
    var playerAdvancementDoneEvent = mutableMapOf<NamespacedKey, PlayerAdvancementDoneEvent.() -> Unit>()


    @EventHandler
    fun onPlayerAdvancementDoneEvent(event: PlayerAdvancementDoneEvent) {
        playerAdvancementDoneEvent[event.advancement.key]!!.invoke(event)
    }

    @EventHandler
    fun onPlayerAdvancementCriterionGrantEvent(event: PlayerAdvancementCriterionGrantEvent) {
        advancementCriterionGrantEvent[event.advancement.key]!!.invoke(event)
    }

    @EventHandler
    fun onInteractDisplay(event: PlayerInteractAtEntityEvent) {
        if (!event.rightClicked.persistentDataContainer.has(
                EVENT_KEY, PersistentDataType.STRING
            )
        ) return

        val id = event.rightClicked.persistentDataContainer.get(
            EVENT_KEY, PersistentDataType.STRING
        )

        displayEvents[id]!!.invoke(event)
    }
    
    @EventHandler
    fun onInteractEvent(event: PlayerInteractEvent) {
        if (interactEvent.isEmpty()) {
            return
        }
        if (event.item != null && event.item!!.itemMeta.persistentDataContainer.has(EVENT_KEY)) {
            val id = event.item!!.itemMeta.persistentDataContainer.get(
                EVENT_KEY,
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
        if (event.recipe.result.persistentDataContainer.has(EVENT_KEY, PersistentDataType.STRING)) {
            val id = event.recipe.result.itemMeta.persistentDataContainer.get(
                EVENT_KEY,
                PersistentDataType.STRING
            )
            craftItemEvent[id]!!.invoke(event)
            return
        }
        return
    }

}