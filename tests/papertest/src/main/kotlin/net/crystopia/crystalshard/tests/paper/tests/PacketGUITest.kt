package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.MINI_MESSAGE
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

class PacketGUITest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {

            // ITEMS
            val item = ItemStack(Material.ARROW)
            val meta = item.itemMeta
            meta.displayName(MINI_MESSAGE.deserialize("<blue>CLICK ME FOR FREE VBUGS</blue>"))
            meta.persistentDataContainer.set(NamespacedKey("testy", "text"), PersistentDataType.STRING, "COOL")
            item.itemMeta = meta

            val item2 = ItemStack(Material.ARROW)
            val meta2 = item2.itemMeta
            meta2.displayName(MINI_MESSAGE.deserialize("<blue>CLICK MEadsad FOR FREE VBUGS</blue>"))
            meta2.persistentDataContainer.set(NamespacedKey("testy", "text"), PersistentDataType.STRING, "TTTTTTTTTTT")
            item2.itemMeta = meta2

            val item3 = ItemStack(Material.GRAY_STAINED_GLASS_PANE)

            packetGUI(
                44,
                Component.text("PacketGUI", NamedTextColor.BLUE),
                MenuType.GENERIC_9x5,
                true,
                CrystalShardPluginTest.instance
            ) {
                player(sender as Player)
                slot(
                    GUI.Data.Slot(
                        item = item,
                        revision = 1,
                        cancel = true,
                        slot = 1,
                    )
                ) { button, click ->
                    println("[ITEM] BUTTON $button")
                    println("[ITEM] CLICK $click")
                }.slot(
                    GUI.Data.Slot(
                        item = item3,
                        revision = 0,
                        cancel = false,
                        slot = 0,
                    )

                ) { button, click ->
                    slot(
                        GUI.Data.Slot(
                            item = item3,
                            revision = 5,
                            cancel = true,
                            slot = 5,
                        )
                    ) { button, click ->

                    }
                }
                open()
                click {
                    println((sender as Player).inventory.getItem(this.slotNum.toInt()))
                    println("CLICK $this")
                }
                buttonClick {
                    println("BUTTON $this")
                }
            }
        }
    }

}