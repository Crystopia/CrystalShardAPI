package net.crystopia.crystalshard.tests.paper.tests

import net.crystopia.crystalshard.paper.custom.extension.craftItemEvent
import net.crystopia.crystalshard.paper.custom.extension.onCraftItem
import net.crystopia.crystalshard.paper.custom.extension.playerInteractWithItemEvent
import net.crystopia.crystalshard.paper.custom.extension.register
import net.crystopia.crystalshard.paper.custom.smart.smartEvent
import net.crystopia.crystalshard.paper.custom.smart.smartRecipe
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapelessRecipe

object EventTest {
    val event = smartEvent<InventoryClickEvent> {
        println("CLICK")
    }
    val recipe = smartRecipe(
        ShapelessRecipe(
            NamespacedKey("test", "test"),
            ItemStack(Material.STONE),
        )
    ) {
        addIngredient(
            Material.WHEAT
        )

        result.playerInteractWithItemEvent {
            println("RESULT")
        }
        result.craftItemEvent {
            println("CRAFT")
        }

        register(true)
        onCraftItem {
            println("Prepare Item")

            if (whoClicked is Player) {
                (whoClicked as Player).health = 0.0
            }
        }
    }
}