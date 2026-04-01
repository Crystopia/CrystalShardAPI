package net.crystopia.crystalshard.paper.custom.smart

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent
import io.papermc.paper.event.entity.EntityDamageItemEvent
import io.papermc.paper.event.player.AsyncChatEvent
import net.crystopia.crystalshard.paper.core.shardInstance
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.event.*
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.*
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.*
import org.bukkit.persistence.PersistentDataType

class CrystalListener : Listener {
    fun unregister() = HandlerList.unregisterAll(this)
}

// Credits to https://github.com/flytegg/twilight/blob/master/src/main/kotlin/gg/flyte/twilight/event/Event.kt
// This is better then my...

inline fun <reified T : Event> event(
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = false,
    crossinline callback: T.() -> Unit,
): CrystalListener = CrystalListener().apply {
    Bukkit.getServer().pluginManager.registerEvent(
        T::class.java,
        this,
        priority,
        { _, event ->
            if (event is T) callback(event)
        },
        shardInstance(),
        ignoreCancelled
    )
}

/**
 *
 * Register SmartEvents for SmartEvent support...
 *
 */

object SmartEvents : Listener {

        val EVENT_KEY: NamespacedKey
            get() = NamespacedKey("crystalshardapievents", "uuid")

        // General
        internal var entityDeathEvent = mutableListOf<EntityDeathEvent.() -> Unit>()
        internal var playerInteractAtEntityEvent = mutableListOf<PlayerInteractAtEntityEvent.() -> Unit>()
        internal var playerMoveEvent = mutableListOf<PlayerMoveEvent.() -> Unit>()
        internal var playerJoinEvent = mutableListOf<PlayerJoinEvent.() -> Unit>()
        internal var asyncChatEvent = mutableListOf<AsyncChatEvent.() -> Unit>()
        internal var playerQuitEvent = mutableListOf<PlayerQuitEvent.() -> Unit>()
        internal var inventoryClickEvent = mutableMapOf<String, InventoryClickEvent.() -> Unit>()
        internal var inventoryCloseEvent = mutableListOf<InventoryCloseEvent.() -> Unit>()
        internal var playerDropItemEvent = mutableListOf<PlayerDropItemEvent.() -> Unit>()
        internal var playerSwapHandItemsEvent = mutableListOf<PlayerSwapHandItemsEvent.() -> Unit>()
        internal var entityPickupItemEvent = mutableListOf<EntityPickupItemEvent.() -> Unit>()
        internal var blockPlaceEvent = mutableListOf<BlockPlaceEvent.() -> Unit>()
        internal var blockBreakEvent = mutableListOf<BlockBreakEvent.() -> Unit>()
        internal var playerDeathEvent = mutableListOf<PlayerDeathEvent.() -> Unit>()
        internal var entityDamageEvent = mutableListOf<EntityDamageEvent.() -> Unit>()
        internal var entityDamageByBlockEvent = mutableListOf<EntityDamageByBlockEvent.() -> Unit>()
        internal var entityDamageByEntityEvent = mutableListOf<EntityDamageByEntityEvent.() -> Unit>()
        internal var entityDamageItemEvent = mutableListOf<EntityDamageItemEvent.() -> Unit>()

        // Other
        internal var interactEvent = mutableMapOf<String, PlayerInteractEvent.() -> Unit>()
        internal var craftItemEvent = mutableMapOf<String, CraftItemEvent.() -> Unit>()
        internal var displayEvents = mutableMapOf<String, PlayerInteractEntityEvent.() -> Unit>()
        internal var advancementCriterionGrantEvent =
            mutableMapOf<NamespacedKey, PlayerAdvancementCriterionGrantEvent.() -> Unit>()
        internal var playerAdvancementDoneEvent = mutableMapOf<NamespacedKey, PlayerAdvancementDoneEvent.() -> Unit>()

        @EventHandler
        private fun onEntityDeathEvent(event: EntityDeathEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            entityDeathEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onPlayerInteractAtEntityEvent(event: PlayerInteractAtEntityEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            playerInteractAtEntityEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onPlayerMoveEvent(event: PlayerMoveEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            playerMoveEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onAsyncChatEvent(event: AsyncChatEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            asyncChatEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onPlayerJoinEvent(event: PlayerJoinEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            playerJoinEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onPlayerQuitEvent(event: PlayerQuitEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            playerQuitEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onEntityDamageItemEvent(event: EntityDamageItemEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            entityDamageItemEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onEntityDamageByEntityEvent(event: EntityDamageByEntityEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            entityDamageByEntityEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onEntityDamageByBlockEvent(event: EntityDamageByBlockEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            entityDamageByBlockEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onEntityDamageEvent(event: EntityDamageEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            entityDamageEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onPlayerDeathEvent(event: PlayerDeathEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            playerDeathEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onBlockBreakEvent(event: BlockBreakEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            blockBreakEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onBlockPlaceEvent(event: BlockPlaceEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            blockPlaceEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onInventoryCloseEvent(event: InventoryCloseEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            inventoryCloseEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onPlayerDropItemEvent(event: PlayerDropItemEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            playerDropItemEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onPlayerSwapHandItemsEvent(event: PlayerSwapHandItemsEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            playerSwapHandItemsEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onEntityPickupItemEvent(event: EntityPickupItemEvent) {
            if (inventoryCloseEvent.isEmpty()) return
            entityPickupItemEvent.forEach { it.invoke(event) }
        }

        @EventHandler
        private fun onInventoryClickEvent(event: InventoryClickEvent) {
            if (inventoryClickEvent.isEmpty()) return
            inventoryClickEvent.forEach { (string, function) ->
                inventoryClickEvent[string]!!.invoke(event)
            }
        }

        @EventHandler
        private fun onPlayerAdvancementDoneEvent(event: PlayerAdvancementDoneEvent) {
            if (!playerAdvancementDoneEvent.contains(event.advancement.key)) return
            playerAdvancementDoneEvent[event.advancement.key]!!.invoke(event)
        }

        @EventHandler
        private fun onPlayerAdvancementCriterionGrantEvent(event: PlayerAdvancementCriterionGrantEvent) {
            if (!advancementCriterionGrantEvent.contains(event.advancement.key)) return
            advancementCriterionGrantEvent[event.advancement.key]!!.invoke(event)
        }

        @EventHandler
        private fun onInteractDisplay(event: PlayerInteractAtEntityEvent) {
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
        private fun onInteractEvent(event: PlayerInteractEvent) {
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
        private fun onCraftItemEvent(event: CraftItemEvent) {
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