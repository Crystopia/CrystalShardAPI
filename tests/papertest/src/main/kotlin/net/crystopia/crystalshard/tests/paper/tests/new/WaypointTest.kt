package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.copyToClipboard
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.waypoints.TrackedWaypoint
import net.crystopia.crystalshard.dhl.shared.data.waypoints.WaypointDataVec3i
import net.crystopia.crystalshard.dhl.shared.data.waypoints.WaypointIcon
import net.crystopia.crystalshard.dhl.shared.enums.waypoints.WaypointOperation
import net.crystopia.crystalshard.dhl.shared.enums.waypoints.WaypointType
import net.crystopia.crystalshard.paper.custom.extension.craftItemEvent
import net.crystopia.crystalshard.paper.custom.extension.onCraftItem
import net.crystopia.crystalshard.paper.custom.extension.playerInteractWithItemEvent
import net.crystopia.crystalshard.paper.custom.extension.register
import net.crystopia.crystalshard.paper.custom.smart.CustomGUI.Companion.openInventory
import net.crystopia.crystalshard.paper.custom.smart.smartEvent
import net.crystopia.crystalshard.paper.custom.smart.smartGUI
import net.crystopia.crystalshard.paper.custom.smart.smartRecipe
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.sendWaypoint
import net.crystopia.crystalshard.paper.pack.font.toGuiRow
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapelessRecipe
import java.util.UUID

class WaypointTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {

    override fun command() {
        test {
            ClientPacketFactory.sendWaypoint(
                WaypointOperation.TRACK,
                TrackedWaypoint(
                    identifier = UUID.randomUUID(),
                    icon = WaypointIcon(
                        style = "default",
                        color = 0x60008000
                    ),
                    type = WaypointType.VEC3I,
                    data = WaypointDataVec3i(
                        10, 10, 10
                    )
                )
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
        }
    }

}

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

class InvFontTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {


    fun reopenInv(player: Player) {
        player.openInventory(basicGui(currentPage))
    }

    fun title(page: Int) = Component.text().toGuiRow(1, null) {
        text("Current Page: $page/4")
        copyToClipboard("Cool text")
    }.toGuiRow(6, null) {
        text("Cool").build()
    }.build()

    var currentPage = 1

    fun basicGui(page: Int) = smartGUI(
        title(page), 54
    ) {
        set(9, ItemStack(Material.ARROW).apply {
            val itemMeta = itemMeta
            itemMeta.isHideTooltip = true
            this.itemMeta = itemMeta
        }) {
            isCancelled = true

            if (page > 1) currentPage -= 1
            else viewer.sendMessage(MiniMessage.miniMessage().deserialize("<red>You are at page one</red>"))

            println(title)

            reopenInv(viewer)
        }
        set(16, ItemStack(Material.ARROW).apply {
            val itemMeta = itemMeta
            itemMeta.isHideTooltip = true
            this.itemMeta = itemMeta
        }) {
            isCancelled = true

            if (page < 4) currentPage += 1
            else viewer.sendMessage(MiniMessage.miniMessage().deserialize("<red>No more pages</red>"))

            reopenInv(viewer)
        }
    }

    override fun command() {
        test {
            (sender as Player).openInventory(basicGui(currentPage))
        }
    }

}