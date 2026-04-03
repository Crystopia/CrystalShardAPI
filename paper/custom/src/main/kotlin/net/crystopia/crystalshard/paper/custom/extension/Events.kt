package net.crystopia.crystalshard.paper.custom.extension

import io.papermc.paper.event.entity.EntityDamageItemEvent
import io.papermc.paper.event.player.AsyncChatEvent
import net.crystopia.crystalshard.paper.custom.smart.SmartEvents
import org.bukkit.NamespacedKey
import org.bukkit.entity.*
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.*
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.event.player.*
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.persistence.PersistentDataType

/**
 * Custom Interaction Method for the PlayerInteractEntityEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Display.playerInteractEntityEvent(
    player: Player,
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
        SmartEvents.displayEvents[player] = action
    }
}

/**
 * Custom Interaction Method for the PlayerInteractEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun ItemStack.playerInteractEvent(
    player: Player,
    action: PlayerInteractEvent.() -> Unit = {}
) {
    SmartEvents.interactEvent[player] = action
}

/**
 * Custom Interaction Method for the PlayerInteractEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun ItemStack.playerInteractWithItemEvent(
    action: PlayerInteractEvent.() -> Unit = {}
) {
    SmartEvents.interactWithItemEvent[this] = action
}

/**
 * Custom Interaction Method for the PlayerInteractEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun ItemStack.playerInteractWithItemHasContainerEvent(
    key: NamespacedKey,
    type: PersistentDataType<*, *>,
    action: PlayerInteractEvent.() -> Unit = {}
) {
    SmartEvents.interactWithItemHasContainerEvent[Pair(this, Pair(key, type))] = action
}

/**
 * Custom Interaction Method for the CraftItemEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun ItemStack.craftItemEvent(action: CraftItemEvent.() -> Unit = {}) {
    SmartEvents.craftItemWithItemEvent[this] = action
}

/**
 * Custom Interaction Method for the InventoryClickEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Inventory.onClick(callback: InventoryClickEvent.() -> Unit) {
    SmartEvents.inventoryClickEvent[this] = callback
}

/**
 * Custom Interaction Method for the InventoryClickEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Inventory.onSlotClick(slot: Int, callback: InventoryClickEvent.() -> Unit) {
    SmartEvents.inventorySlotClickEvent[Pair(this, slot)] = callback
}

/**
 * Custom Interaction Method for the InventoryCloseEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Inventory.onClose(callback: InventoryCloseEvent.() -> Unit) {
    SmartEvents.inventoryCloseEvent[this] = callback
}

/**
 * Custom Interaction Method for the InventoryClickEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onInventoryClick(callback: InventoryClickEvent.() -> Unit) {
    SmartEvents.inventoryClickEvent[this.inventory] = callback
}

/**
 * Custom Interaction Method for the PlayerJoinEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onJoin(callback: PlayerJoinEvent.() -> Unit) {
    SmartEvents.playerJoinEvent[this] = callback
}

/**
 * Custom Interaction Method for the PlayerQuitEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onQuit(callback: PlayerQuitEvent.() -> Unit) {
    SmartEvents.playerQuitEvent[this] = callback
}

/**
 * Custom Interaction Method for the AsyncChatEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onChat(callback: AsyncChatEvent.() -> Unit) {
    SmartEvents.asyncChatEvent[this] = callback
}

/**
 * Custom Interaction Method for the InventoryCloseEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onInventoryClose(callback: InventoryCloseEvent.() -> Unit) {
    SmartEvents.inventoryCloseEvent[this.inventory] = callback
}

/**
 * Custom Interaction Method for the PlayerDropItemEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onDrop(callback: PlayerDropItemEvent.() -> Unit) {
    SmartEvents.playerDropItemEvent[this] = callback
}

/**
 * Custom Interaction Method for the PlayerSwapHandItemsEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onItemSwap(callback: PlayerSwapHandItemsEvent.() -> Unit) {
    SmartEvents.playerSwapHandItemsEvent[this] = callback
}

/**
 * Custom Interaction Method for the EntityPickupItemEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onPickUp(callback: EntityPickupItemEvent.() -> Unit) {
    SmartEvents.entityPickupItemEvent[this] = callback
}

/**
 * Custom Interaction Method for the BlockPlaceEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onBlockPlace(callback: BlockPlaceEvent.() -> Unit) {
    SmartEvents.blockPlaceEvent[this] = callback
}

/**
 * Custom Interaction Method for the BlockBreakEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onBlockBreak(callback: BlockBreakEvent.() -> Unit) {
    SmartEvents.blockBreakEvent[this] = callback
}

/**
 * Custom Interaction Method for the PlayerDeathEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onDeath(callback: PlayerDeathEvent.() -> Unit) {
    SmartEvents.playerDeathEvent[this] = callback
}

/**
 * Custom Interaction Method for the PlayerMoveEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onMove(callback: PlayerMoveEvent.() -> Unit) {
    SmartEvents.playerMoveEvent[this] = callback
}

/**
 * Custom Interaction Method for the PlayerInteractAtEntityEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Player.onInteractEntity(callback: PlayerInteractAtEntityEvent.() -> Unit) {
    SmartEvents.playerInteractAtEntityEvent[this] = callback
}

/**
 * Custom Interaction Method for the EntityDeathEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onDeath(callback: EntityDeathEvent.() -> Unit) {
    SmartEvents.entityDeathEvent[this] = callback
}

/**
 * Custom Interaction Method for the EntityDamageEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onDamage(callback: EntityDamageEvent.() -> Unit) {
    SmartEvents.entityDamageEvent[this] = callback
}

/**
 * Custom Interaction Method for the EntityDamageByBlockEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onDamageByBlock(callback: EntityDamageByBlockEvent.() -> Unit) {
    SmartEvents.entityDamageByBlockEvent[this] = callback
}

/**
 * Custom Interaction Method for the EntityDamageByEntityEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onDamageByEntity(callback: EntityDamageByEntityEvent.() -> Unit) {
    SmartEvents.entityDamageByEntityEvent[this] = callback
}

/**
 * Custom Interaction Method for the EntityDamageItemEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Entity.onItemDamage(callback: EntityDamageItemEvent.() -> Unit) {
    SmartEvents.entityDamageItemEvent[this] = callback
}

/**
 * Custom Interaction Method for the CraftItemEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Recipe.onCraftItem(callback: CraftItemEvent.() -> Unit) {
    SmartEvents.craftItemEvent[this] = callback
}

/**
 * Custom Interaction Method for the PrepareItemCraftEvent.
 * Register SmartEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.smart.SmartEvents
 */
fun Recipe.onPrepareCraftItem(callback: PrepareItemCraftEvent.() -> Unit) {
    SmartEvents.prepareItemCraftEvent[this] = callback
}
