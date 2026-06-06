package net.crystopia.crystalshard.tests.paper

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import net.crystopia.crystalshard.common.config.ConfigType
import net.crystopia.crystalshard.common.config.config
import net.crystopia.crystalshard.common.database.custom.database
import net.crystopia.crystalshard.common.extension.cmpb
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.common.log.Log
import net.crystopia.crystalshard.common.log.green
import net.crystopia.crystalshard.common.log.red
import net.crystopia.crystalshard.dhl.PacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.recipes.RecipeBookCategories
import net.crystopia.crystalshard.paper.core.crystalshard
import net.crystopia.crystalshard.paper.custom.smart.SmartEvents
import net.crystopia.crystalshard.paper.custom.smart.smartEvent
import net.crystopia.crystalshard.paper.custom.smart.smartRecipe
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.placeGhostRecipe
import net.crystopia.crystalshard.paper.dhl.packets.server.containerClickEvent
import net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry
import net.crystopia.crystalshard.paper.folia.threadedTask
import net.crystopia.crystalshard.tests.paper.config.TestConfig
import net.crystopia.crystalshard.tests.paper.databse.entities.UserEntity
import net.crystopia.crystalshard.tests.paper.databse.models.users
import net.crystopia.crystalshard.tests.paper.tests.new.AdvancementTabTest
import net.crystopia.crystalshard.tests.paper.tests.new.AdvancementTest
import net.crystopia.crystalshard.tests.paper.tests.new.EventTest
import net.crystopia.crystalshard.tests.paper.tests.new.RecipeTest
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin
import org.ktorm.entity.add
import java.io.File

class CrystalShardPluginTest : JavaPlugin() {

    companion object {
        lateinit var instance: CrystalShardPluginTest
    }

    init {
        instance = this
    }

    val testConfig = config<TestConfig>(
        File("plugins/crystalshard/tests/paper.yml"),
        ConfigType.YAML
    ) {
        save(TestConfig())
        load(TestConfig())
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
            println("eier")
            PacketFactory.server.containerClickEvent(
                items = mutableListOf(),
                player = player,
                name = NamespacedKey("fsdf", "sdf"),
                shouldPublish = true
            ) {
                println(this.containerId)
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
                        category = RecipeBookCategories.MISC
                    )
                ) {
                    it.send(mutableListOf(player))
                }
            }
        }

        server.broadcast(cmpb("<red>ERROR</red>").build())
        val cmp = cmpb("<red>ERROR</red>").text("<blue>ERROR</blue>").text("text", "font")

        println("Ohh this is a config value \"${testConfig.data.consoleMessage ?: "No Data"}\"".red() + "GREEN".green())
        testConfig.data.consoleMessage = "UPDATE"
        testConfig.save(testConfig.data)
        println("Ohh this is a config value \"${testConfig.data.consoleMessage ?: "No Data"}\"")

        EventTest
        EventTest.recipe

        AdvancementTest
        RecipeTest
        AdvancementTabTest

        server.pluginManager.registerEvents(SmartEvents, this)
        Log.info("Plugin loaded!")
    }

    override fun onDisable() {

    }

    fun database() {
        // DATABASE TEST
        database(
            "jdbc:postgresql://localhost:5432/postgres",
            "postgres",
            "password",
        ) {
            connect()
            command(
                """
               CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL,
                                     name TEXT,
                                     password TEXT,
                                     enabled BOOLEAN
               );
            """.trimIndent()
            )

            val user = UserEntity {
                name = "test"
                password = "test"
                enabled = true
            }
            database.users.add(user)
        }
    }
}