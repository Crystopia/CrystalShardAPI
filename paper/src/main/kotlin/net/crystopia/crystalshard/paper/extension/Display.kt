package net.crystopia.crystalshard.paper.extension

import gg.flyte.twilight.extension.spawnEntity
import net.crystopia.crystalshard.paper.custom.CrystalEvents
import org.bukkit.entity.Display
import org.bukkit.entity.EntityType
import org.bukkit.entity.Interaction
import org.bukkit.event.player.PlayerInteractEntityEvent
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
    val interaction = location.spawnEntity(EntityType.INTERACTION) as Interaction

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