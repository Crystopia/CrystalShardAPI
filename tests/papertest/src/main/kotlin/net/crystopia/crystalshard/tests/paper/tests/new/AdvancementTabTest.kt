package net.crystopia.crystalshard.tests.paper.tests.new

import net.crystopia.crystalshard.dhl.PacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.gui.MenuType
import net.crystopia.crystalshard.paper.box.GUI
import net.crystopia.crystalshard.paper.box.packetGUI
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.selectAdvancementTab
import net.crystopia.crystalshard.paper.dhl.packets.server.seenAdvancementsEvent
import net.crystopia.crystalshard.tests.paper.CrystalShardPluginTest
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object AdvancementTabTest : Test("AdvancementTabTest") {

    override fun command() {
        test {
            val player = Bukkit.getPlayer("_jespersen")!!
            PacketFactory.server.seenAdvancementsEvent(
                player,
                NamespacedKey("event", "advtab"),
                true
            ) {
                println(this.tab.key)
                if (this.tab.key != "advancement") return@seenAdvancementsEvent
                val item = ItemStack(Material.GRAY_STAINED_GLASS_PANE)
                packetGUI(
                    44,
                    Component.text("PacketGUI", NamedTextColor.BLUE),
                    MenuType.GENERIC_9x5,
                    true,
                    CrystalShardPluginTest.instance
                ) {
                    player(sender!! as Player)
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
                            item = item,
                            revision = 0,
                            cancel = false,
                            slot = 0,
                        )

                    ) { button, click ->
                        slot(
                            GUI.Data.Slot(
                                item = item,
                                revision = 5,
                                cancel = true,
                                slot = 5,
                            )
                        ) { button, click ->

                        }
                    }
                    open()
                    click {
                        println((sender!! as Player).inventory.getItem(this.slotNum.toInt()))
                        println("CLICK $this")
                    }
                    buttonClick {
                        println("BUTTON $this")
                    }
                }
            }

            PacketFactory.client.selectAdvancementTab(NamespacedKey("minecraft", "story/root")) {
                it.send(mutableListOf(player))
            }
        }
    }
}