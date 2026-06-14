package net.crystopia.crystalshard.tests.paper

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import net.crystopia.crystalshard.common.components.cmpb
import net.crystopia.crystalshard.common.components.text
import net.crystopia.crystalshard.dhl.PacketFactory
import net.crystopia.crystalshard.paper.core.crystalshard
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.placeGhostRecipe
import net.crystopia.crystalshard.paper.dhl.packets.server.containerClickEvent
import net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry
import net.crystopia.crystalshard.paper.folia.threadedTask
import net.crystopia.crystalshard.paper.util.smart.SmartEvents
import net.crystopia.crystalshard.paper.util.smart.smartEvent
import net.crystopia.crystalshard.paper.util.smart.smartRecipe
import net.crystopia.crystalshard.tests.paper.tests.new.AdvancementTabTest
import net.crystopia.crystalshard.tests.paper.tests.new.AdvancementTest
import net.crystopia.crystalshard.tests.paper.tests.new.RecipeTest
import net.crystopia.crystalshard.tests.paper.tests.new.UpdateAttributesTest
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.recipe.CraftingBookCategory
import org.bukkit.plugin.java.JavaPlugin

class CrystalShardPluginTest : JavaPlugin() {

    companion object {
        lateinit var instance: CrystalShardPluginTest
    }

    init {
        instance = this
    }

    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIPaperConfig(this).silentLogs(true))
    }

    override fun onEnable() {
        crystalshard(this)
        CommandAPI.onEnable();

        threadedTask {
            println("Oh hello on main thread")
        }

        val event = smartEvent<PlayerJoinEvent> {
            PacketFactory.server.containerClickEvent(
                items = mutableListOf(),
                player = player,
                name = NamespacedKey("fsdf", "sdf"),
                shouldPublish = true
            ) {
                PacketFactory.client.placeGhostRecipe(
                    containerId = this.containerId,
                    recipe = RecipeEntry(
                        id = NamespacedKey("recipe", "egg2"),
                        recipe = smartRecipe(
                            ShapedRecipe(
                                NamespacedKey("recipe", "egg2"),
                                ItemStack(Material.EGG),
                            )
                        ) {
                            category = CraftingBookCategory.EQUIPMENT
                            shape(
                                "AAA",
                                "ACA",
                                "AAA"
                            )
                            setIngredient(
                                'C',
                                Material.CHICKEN_SPAWN_EGG
                            )
                            setIngredient(
                                'A',
                                Material.APPLE
                            )
                        },
                        showNotification = 0x00,
                        highlight = 0x00,
                        group = "eier",
                    )
                ) {
                    it.send(mutableListOf(player))
                }
            }
        }

        server.broadcast(cmpb("<red>ERROR</red>").build())
        val cmp = cmpb("<red>ERROR</red>").text("<blue>ERROR</blue>").text("text", "font")

        AdvancementTest
        RecipeTest
        UpdateAttributesTest
        AdvancementTabTest

        server.pluginManager.registerEvents(SmartEvents, this)
    }

    override fun onDisable() {

    }
}