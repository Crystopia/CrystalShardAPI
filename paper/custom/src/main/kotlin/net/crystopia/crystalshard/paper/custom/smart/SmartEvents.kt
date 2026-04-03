package net.crystopia.crystalshard.paper.custom.smart

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent
import io.papermc.paper.event.entity.EntityDamageItemEvent
import io.papermc.paper.event.player.AsyncChatEvent
import net.crystopia.crystalshard.paper.core.shardInstance
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.*
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.*
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.event.player.*
import org.bukkit.inventory.CraftingRecipe
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.persistence.PersistentDataType

class CrystalListener : Listener {
    fun unregister() = HandlerList.unregisterAll(this)
}

// Credits to https://github.com/flytegg/twilight/blob/master/src/main/kotlin/gg/flyte/twilight/event/Event.kt
// This is better then my...

inline fun <reified T : Event> smartEvent(
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

    internal val EVENT_KEY: NamespacedKey
        get() =
            NamespacedKey("crystalshardapievents", "uuid")

    // General
    internal var entityDeathEvent = mutableMapOf<Entity, EntityDeathEvent.() -> Unit>()
    internal var playerInteractAtEntityEvent = mutableMapOf<Player, PlayerInteractAtEntityEvent.() -> Unit>()
    internal var playerMoveEvent = mutableMapOf<Player, PlayerMoveEvent.() -> Unit>()
    internal var playerJoinEvent = mutableMapOf<Player, PlayerJoinEvent.() -> Unit>()
    internal var asyncChatEvent = mutableMapOf<Player, AsyncChatEvent.() -> Unit>()
    internal var playerQuitEvent = mutableMapOf<Player, PlayerQuitEvent.() -> Unit>()
    internal var inventoryClickEvent = mutableMapOf<Inventory, InventoryClickEvent.() -> Unit>()
    internal var inventorySlotClickEvent = mutableMapOf<Pair<Inventory, Int>, InventoryClickEvent.() -> Unit>()
    internal var inventoryCloseEvent = mutableMapOf<Inventory, InventoryCloseEvent.() -> Unit>()
    internal var playerDropItemEvent = mutableMapOf<Player, PlayerDropItemEvent.() -> Unit>()
    internal var playerSwapHandItemsEvent = mutableMapOf<Player, PlayerSwapHandItemsEvent.() -> Unit>()
    internal var entityPickupItemEvent = mutableMapOf<Entity, EntityPickupItemEvent.() -> Unit>()
    internal var blockPlaceEvent = mutableMapOf<Player, BlockPlaceEvent.() -> Unit>()
    internal var blockBreakEvent = mutableMapOf<Player, BlockBreakEvent.() -> Unit>()
    internal var playerDeathEvent = mutableMapOf<Player, PlayerDeathEvent.() -> Unit>()
    internal var entityDamageEvent = mutableMapOf<Entity, EntityDamageEvent.() -> Unit>()
    internal var entityDamageByBlockEvent = mutableMapOf<Entity, EntityDamageByBlockEvent.() -> Unit>()
    internal var entityDamageByEntityEvent = mutableMapOf<Entity, EntityDamageByEntityEvent.() -> Unit>()
    internal var entityDamageItemEvent = mutableMapOf<Entity, EntityDamageItemEvent.() -> Unit>()

    // Other
    internal var interactEvent = mutableMapOf<Player, PlayerInteractEvent.() -> Unit>()
    internal var interactWithItemEvent = mutableMapOf<ItemStack, PlayerInteractEvent.() -> Unit>()
    internal var interactWithItemHasContainerEvent =
        mutableMapOf<Pair<ItemStack, Pair<NamespacedKey, PersistentDataType<*, *>>>, PlayerInteractEvent.() -> Unit>()
    internal var craftItemEvent = mutableMapOf<Recipe, CraftItemEvent.() -> Unit>()
    internal var craftItemWithItemEvent = mutableMapOf<ItemStack, CraftItemEvent.() -> Unit>()
    internal var prepareItemCraftEvent = mutableMapOf<Recipe, PrepareItemCraftEvent.() -> Unit>()
    internal var displayEvents = mutableMapOf<Player, PlayerInteractEntityEvent.() -> Unit>()
    internal var advancementCriterionGrantEvent =
        mutableMapOf<NamespacedKey, PlayerAdvancementCriterionGrantEvent.() -> Unit>()
    internal var playerAdvancementDoneEvent = mutableMapOf<NamespacedKey, PlayerAdvancementDoneEvent.() -> Unit>()

    @EventHandler
    private fun onPrepareItemCraftEvent(event: PrepareItemCraftEvent) {
        prepareItemCraftEvent.forEach { (recipe, function) ->
            if ((event.recipe as CraftingRecipe).key == (recipe as CraftingRecipe).key) function.invoke(event)
        }
    }

        @EventHandler
        private fun onEntityDeathEvent(event: EntityDeathEvent) {
            entityDeathEvent.forEach { if (it.key.entityId == event.entity.entityId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onPlayerInteractAtEntityEvent(event: PlayerInteractAtEntityEvent) {
            playerInteractAtEntityEvent.forEach { if (event.player.entityId == it.key.entityId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onPlayerMoveEvent(event: PlayerMoveEvent) {
            playerMoveEvent.forEach {
                if (it.key.uniqueId == event.player.uniqueId) it.value.invoke(event)
            }
        }

        @EventHandler
        private fun onAsyncChatEvent(event: AsyncChatEvent) {
            asyncChatEvent.forEach { if (event.player.uniqueId == it.key.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onPlayerJoinEvent(event: PlayerJoinEvent) {
            playerJoinEvent.forEach { if (event.player.uniqueId == it.key.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onPlayerQuitEvent(event: PlayerQuitEvent) {
            playerQuitEvent.forEach { if (event.player.uniqueId == event.player.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onEntityDamageItemEvent(event: EntityDamageItemEvent) {
            entityDamageItemEvent.forEach { if (event.entity.uniqueId == it.key.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onEntityDamageByEntityEvent(event: EntityDamageByEntityEvent) {
            entityDamageByEntityEvent.forEach { if (event.entity.uniqueId == it.key.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onEntityDamageByBlockEvent(event: EntityDamageByBlockEvent) {
            entityDamageByBlockEvent.forEach { if (it.key.uniqueId == event.entity.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onEntityDamageEvent(event: EntityDamageEvent) {
            entityDamageEvent.forEach { if (it.key.uniqueId == event.entity.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onPlayerDeathEvent(event: PlayerDeathEvent) {
            playerDeathEvent.forEach { if (event.entity.uniqueId == event.player.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onBlockBreakEvent(event: BlockBreakEvent) {
            blockBreakEvent.forEach { if (event.player.uniqueId == event.player.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onBlockPlaceEvent(event: BlockPlaceEvent) {
            blockPlaceEvent.forEach { if (event.player.uniqueId == event.player.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onInventoryCloseEvent(event: InventoryCloseEvent) {
            inventoryCloseEvent.forEach { if (it.key == event.inventory) it.value.invoke(event) }
        }

        @EventHandler
        private fun onPlayerDropItemEvent(event: PlayerDropItemEvent) {
            playerDropItemEvent.forEach { if (event.player.uniqueId == event.player.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onPlayerSwapHandItemsEvent(event: PlayerSwapHandItemsEvent) {
            playerSwapHandItemsEvent.forEach { if (event.player.uniqueId == event.player.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onEntityPickupItemEvent(event: EntityPickupItemEvent) {
            entityPickupItemEvent.forEach { if (event.entity.uniqueId == it.key.uniqueId) it.value.invoke(event) }
        }

        @EventHandler
        private fun onInventoryClickEvent(event: InventoryClickEvent) {
            inventoryClickEvent.forEach { (inv, function) ->
                if (event.inventory == inv) function.invoke(event)
            }
            inventorySlotClickEvent.forEach { (pair, function) ->
                if (pair.first == event.inventory) {
                    if (event.slot == pair.second) function.invoke(event)
                }
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
            displayEvents.forEach { (player, function) ->
                if (player.uniqueId == event.player.uniqueId) function.invoke(event)
            }
        }

        @EventHandler
        private fun onInteractEvent(event: PlayerInteractEvent) {
            interactEvent.forEach { (player, function) ->
                if (player.uniqueId == event.player.uniqueId) function.invoke(event)
            }
            interactWithItemEvent.forEach { (stack, function) ->
                if (event.item == stack) {
                    function.invoke(event)
                }
            }
            interactWithItemHasContainerEvent.forEach { (pair, function) ->
                if (
                    event.item?.persistentDataContainer?.has(pair.second.first) == true
                ) {
                    if (
                        event.item?.persistentDataContainer?.get(pair.second.first, pair.second.second)
                        ==
                        pair.first.persistentDataContainer.get(pair.second.first, pair.second.second)

                    ) function.invoke(event)
                }
            }
        }

        @EventHandler
        private fun onCraftItemEvent(event: CraftItemEvent) {
            craftItemEvent.forEach { (recipe, function) ->
                if ((event.recipe as CraftingRecipe).key == (recipe as CraftingRecipe).key) function.invoke(event)
            }
            craftItemWithItemEvent.forEach {
                if (it.key == (event.recipe as CraftingRecipe).result) it.value.invoke(event)
            }
        }
}