package net.crystopia.crystalshard.tests.paper.tests

import net.crystopia.crystalshard.paper.custom.smart.event
import org.bukkit.event.inventory.InventoryClickEvent

object EventTest {
    val event = event<InventoryClickEvent> {
        println("CLICK")
    }
}