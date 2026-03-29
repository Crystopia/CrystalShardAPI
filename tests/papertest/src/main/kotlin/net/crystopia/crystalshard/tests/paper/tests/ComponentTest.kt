package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.MINI_MESSAGE
import net.crystopia.crystalshard.common.extension.cmp
import net.crystopia.crystalshard.paper.box.GUI
import net.crystopia.crystalshard.paper.box.packetGUI
import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.MenuType
import net.crystopia.crystalshard.tests.paper.CrystalShardPluginTest
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class ComponentTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            sender.sendMessage(cmp("<red>MiniMessage</red>"))
        }
    }

}