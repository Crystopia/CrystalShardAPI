package net.crystopia.crystalshard.paper.custom.extension

import net.crystopia.crystalshard.paper.custom.crystal.CrystalEvents
import org.bukkit.entity.*
import org.bukkit.event.*
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import kotlin.text.isNullOrEmpty

/**
 * Custom Interaction Method for the PlayerInteractEntityEvent.
 * Register CrystalEvents and use the Event Manager.
 * @see net.crystopia.crystalshard.paper.custom.CrystalEvents
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
        CrystalEvents.EVENT_KEY, PersistentDataType.STRING, uniqueId.toString()
    )

    // Display
    persistentDataContainer.set(
        CrystalEvents.EVENT_KEY, PersistentDataType.STRING, uniqueId.toString()
    )

    val id = interaction.persistentDataContainer.get(
        CrystalEvents.EVENT_KEY, PersistentDataType.STRING
    )

    if (id.isNullOrEmpty()) {
        throw Exception("Item has no event interact Key!")
    } else {
        CrystalEvents.displayEvents[id] = action
    }
}

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