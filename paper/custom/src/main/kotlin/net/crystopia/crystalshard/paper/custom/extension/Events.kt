package net.crystopia.crystalshard.paper.custom.extension

import io.papermc.paper.event.entity.EntityDamageItemEvent
import io.papermc.paper.event.player.AsyncChatEvent
import net.crystopia.crystalshard.paper.custom.smart.SmartEvents
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
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
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
        SmartEvents.EVENT_KEY, PersistentDataType.STRING, uniqueId.toString()
    )

    // Display
    persistentDataContainer.set(
        SmartEvents.EVENT_KEY, PersistentDataType.STRING, uniqueId.toString()
    )

    val id = interaction.persistentDataContainer.get(
        SmartEvents.EVENT_KEY, PersistentDataType.STRING
    )

    if (id.isNullOrEmpty()) {
        throw Exception("Item has no event interact Key!")
    } else {
        SmartEvents.displayEvents[id] = action
    }
}

/**
 * Custom Interaction Method for the PlayerInteractEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun ItemStack.playerInteractEvent(action: PlayerInteractEvent.() -> Unit = {}) {
    val id = itemMeta.persistentDataContainer.get(
        SmartEvents.EVENT_KEY, PersistentDataType.STRING
    )
    if (id.isNullOrEmpty()) {
        throw Exception("Item has no event interact Key!")
    } else {
        SmartEvents.interactEvent[id] = action
    }
}

/**
 * Custom Interaction Method for the CraftItemEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun ItemStack.craftItemEvent(action: CraftItemEvent.() -> Unit = {}) {
    val id = itemMeta.persistentDataContainer.get(
        SmartEvents.EVENT_KEY, PersistentDataType.STRING
    )
    if (id.isNullOrEmpty()) {
        throw Exception("Item has no event interact Key!")
    } else {
        SmartEvents.craftItemEvent[id] = action
    }
}

/**
 * Custom Interaction Method for the InventoryClickEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Inventory.onClick(callback: InventoryClickEvent.() -> Unit) {
    SmartEvents.inventoryClickEvent[UUID.randomUUID().toString()] = callback
}

/**
 * Custom Interaction Method for the InventoryClickEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Inventory.onSlotClick(slot: Int, callback: InventoryClickEvent.() -> Unit) {
    SmartEvents.inventoryClickEvent[slot.toString()] = callback
}

/**
 * Custom Interaction Method for the InventoryCloseEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Inventory.onClose(callback: InventoryCloseEvent.() -> Unit) {
    SmartEvents.inventoryCloseEvent.add(callback)
}

/**
 * Custom Interaction Method for the InventoryClickEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onInventoryClick(callback: InventoryClickEvent.() -> Unit) {
    SmartEvents.inventoryClickEvent[UUID.randomUUID().toString()] = callback
}

/**
 * Custom Interaction Method for the PlayerJoinEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onJoin(callback: PlayerJoinEvent.() -> Unit) {
    SmartEvents.playerJoinEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerQuitEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onQuit(callback: PlayerQuitEvent.() -> Unit) {
    SmartEvents.playerQuitEvent.add(callback)
}

/**
 * Custom Interaction Method for the AsyncChatEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onChat(callback: AsyncChatEvent.() -> Unit) {
    SmartEvents.asyncChatEvent.add(callback)
}

/**
 * Custom Interaction Method for the InventoryCloseEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onInventoryClose(callback: InventoryCloseEvent.() -> Unit) {
    SmartEvents.inventoryCloseEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerDropItemEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onDrop(callback: PlayerDropItemEvent.() -> Unit) {
    SmartEvents.playerDropItemEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerSwapHandItemsEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onItemSwap(callback: PlayerSwapHandItemsEvent.() -> Unit) {
    SmartEvents.playerSwapHandItemsEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityPickupItemEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onPickUp(callback: EntityPickupItemEvent.() -> Unit) {
    SmartEvents.entityPickupItemEvent.add(callback)
}

/**
 * Custom Interaction Method for the BlockPlaceEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onBlockPlace(callback: BlockPlaceEvent.() -> Unit) {
    SmartEvents.blockPlaceEvent.add(callback)
}

/**
 * Custom Interaction Method for the BlockBreakEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onBlockBreak(callback: BlockBreakEvent.() -> Unit) {
    SmartEvents.blockBreakEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerDeathEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onDeath(callback: PlayerDeathEvent.() -> Unit) {
    SmartEvents.playerDeathEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerMoveEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onMove(callback: PlayerMoveEvent.() -> Unit) {
    SmartEvents.playerMoveEvent.add(callback)
}

/**
 * Custom Interaction Method for the PlayerInteractAtEntityEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onInteractEntity(callback: PlayerInteractAtEntityEvent.() -> Unit) {
    SmartEvents.playerInteractAtEntityEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityDeathEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onDeath(callback: EntityDeathEvent.() -> Unit) {
    SmartEvents.entityDeathEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityDamageEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onDamage(callback: EntityDamageEvent.() -> Unit) {
    SmartEvents.entityDamageEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityDamageByBlockEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onDamageByBlock(callback: EntityDamageByBlockEvent.() -> Unit) {
    SmartEvents.entityDamageByBlockEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityDamageByEntityEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onDamageByEntity(callback: EntityDamageByEntityEvent.() -> Unit) {
    SmartEvents.entityDamageByEntityEvent.add(callback)
}

/**
 * Custom Interaction Method for the EntityDamageItemEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onItemDamage(callback: EntityDamageItemEvent.() -> Unit) {
    SmartEvents.entityDamageItemEvent.add(callback)
}