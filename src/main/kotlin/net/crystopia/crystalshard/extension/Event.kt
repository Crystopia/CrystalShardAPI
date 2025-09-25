package net.crystopia.crystalshard.extension

import net.crystopia.crystalshard.events.CrystalEvents
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

fun ItemStack.interactEvent(action: PlayerInteractEvent.() -> Unit = {}) {
    val id = itemMeta.persistentDataContainer.get(
        CrystalEvents.key, PersistentDataType.STRING
    )
    if (id.isNullOrEmpty()) {
        throw Exception("Item has no event interact Key!")
    } else {
        CrystalEvents.interactEvent[id] = action
    }
}

fun ItemStack.craftItemEvent(action: CraftItemEvent.() -> Unit = {}) {
    val id = itemMeta.persistentDataContainer.get(
        CrystalEvents.key, PersistentDataType.STRING
    )
    if (id.isNullOrEmpty()) {
        throw Exception("Item has no event interact Key!")
    } else {
        CrystalEvents.craftItemEvent[id] = action
    }
}