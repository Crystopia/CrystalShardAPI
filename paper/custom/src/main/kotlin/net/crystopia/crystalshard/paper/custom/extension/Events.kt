package net.crystopia.crystalshard.paper.custom.extension

import io.papermc.paper.event.entity.EntityDamageItemEvent
import io.papermc.paper.event.player.AsyncChatEvent
import net.crystopia.crystalshard.paper.custom.smart.Events
import org.bukkit.entity.Display
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Interaction
import org.bukkit.entity.Player
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByBlockEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*

/**
 * Custom Interaction Method for the PlayerInteractEntityEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Display.playerInteractEntityEvent(
    interactionHeight: Float?,
    interactionWidth: Float?, action: PlayerInteractEntityEvent.() -> Unit = {}
) {
    val interaction = location.world.spawnEntity(location, EntityType.INTERACTION) as Interaction

    if (interactionWidth != null) {
        interaction.interactionWidth = 2.0F
    }
    if (interactionHeight != null) {
        interaction.interactionHeight = 2.0F
    }

    interaction.isResponsive = true
    // interaction.isInvisible = true

    // Interaction
    interaction.persistentDataContainer.set(
        Events.EVENT_KEY, PersistentDataType.STRING, uniqueId.toString()
    )

    // Display
    persistentDataContainer.set(
        Events.EVENT_KEY, PersistentDataType.STRING, uniqueId.toString()
    )

    val id = interaction.persistentDataContainer.get(
        Events.EVENT_KEY, PersistentDataType.STRING
    )

    if (id.isNullOrEmpty()) {
        throw Exception("Item has no event interact Key!")
    } else {
        Events.displayEvents[id] = action
    }
}

/**
 * Custom Interaction Method for the PlayerInteractEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun ItemStack.playerInteractEvent(action: PlayerInteractEvent.() -> Unit = {}) {
    val id = itemMeta.persistentDataContainer.get(
        Events.EVENT_KEY, PersistentDataType.STRING
    )
    if (id.isNullOrEmpty()) {
        throw Exception("Item has no event interact Key!")
    } else {
        Events.interactEvent[id] = action
    }
}

/**
 * Custom Interaction Method for the CraftItemEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun ItemStack.craftItemEvent(action: CraftItemEvent.() -> Unit = {}) {
    val id = itemMeta.persistentDataContainer.get(
        Events.EVENT_KEY, PersistentDataType.STRING
    )
    if (id.isNullOrEmpty()) {
        throw Exception("Item has no event interact Key!")
    } else {
        Events.craftItemEvent[id] = action
    }
}

/**
 * Custom Interaction Method for the InventoryClickEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Inventory.onClick(callback: InventoryClickEvent.() -> Unit) {
    Events.inventoryClickEvent[UUID.randomUUID().toString()] = callback
}

/**
 * Custom Interaction Method for the InventoryClickEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Inventory.onSlotClick(slot: Int, callback: InventoryClickEvent.() -> Unit) {
    Events.inventoryClickEvent[slot.toString()] = callback
}

/**
 * Custom Interaction Method for the InventoryCloseEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Inventory.onClose(callback: InventoryCloseEvent.() -> Unit) {
    Events.inventoryCloseEvent.add(callback)
}

/**
 * Custom Interaction Method for the InventoryClickEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onInventoryClick(callback: InventoryClickEvent.() -> Unit) {
    Events.inventoryClickEvent[UUID.randomUUID().toString()] = callback
}

/**
 * Custom Interaction Method for the PlayerJoinEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onJoin(callback: PlayerJoinEvent.() -> Unit) {
    Events.playerJoinEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerQuitEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onQuit(callback: PlayerQuitEvent.() -> Unit) {
    Events.playerQuitEvent.add(callback)
}

/**
 * Custom Interaction Method for the AsyncChatEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onChat(callback: AsyncChatEvent.() -> Unit) {
    Events.asyncChatEvent.add(callback)
}

/**
 * Custom Interaction Method for the InventoryCloseEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onInventoryClose(callback: InventoryCloseEvent.() -> Unit) {
    Events.inventoryCloseEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerDropItemEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onDrop(callback: PlayerDropItemEvent.() -> Unit) {
    Events.playerDropItemEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerSwapHandItemsEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onItemSwap(callback: PlayerSwapHandItemsEvent.() -> Unit) {
    Events.playerSwapHandItemsEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityPickupItemEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Entity.onPickUp(callback: EntityPickupItemEvent.() -> Unit) {
    Events.entityPickupItemEvent.add(callback)
}

/**
 * Custom Interaction Method for the BlockPlaceEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onBlockPlace(callback: BlockPlaceEvent.() -> Unit) {
    Events.blockPlaceEvent.add(callback)
}

/**
 * Custom Interaction Method for the BlockBreakEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onBlockBreak(callback: BlockBreakEvent.() -> Unit) {
    Events.blockBreakEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerDeathEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onDeath(callback: PlayerDeathEvent.() -> Unit) {
    Events.playerDeathEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerMoveEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onMove(callback: PlayerMoveEvent.() -> Unit) {
    Events.playerMoveEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerInteractAtEntityEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Player.onInteractEntity(callback: PlayerInteractAtEntityEvent.() -> Unit) {
    Events.playerInteractAtEntityEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityDeathEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Entity.onDeath(callback: EntityDeathEvent.() -> Unit) {
    Events.entityDeathEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityDamageEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Entity.onDamage(callback: EntityDamageEvent.() -> Unit) {
    Events.entityDamageEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityDamageByBlockEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Entity.onDamageByBlock(callback: EntityDamageByBlockEvent.() -> Unit) {
    Events.entityDamageByBlockEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityDamageByEntityEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Entity.onDamageByEntity(callback: EntityDamageByEntityEvent.() -> Unit) {
    Events.entityDamageByEntityEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityDamageItemEvent.
 * Register Events and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.Events
 */
fun Entity.onItemDamage(callback: EntityDamageItemEvent.() -> Unit) {
    Events.entityDamageItemEvent.add(callback)
}