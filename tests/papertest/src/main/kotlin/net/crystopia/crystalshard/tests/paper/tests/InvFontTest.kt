package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.copyToClipboard
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.custom.smart.CustomGUI.Companion.openInventory
import net.crystopia.crystalshard.paper.custom.smart.smartGUI
import net.crystopia.crystalshard.paper.pack.font.toGuiRow
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

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