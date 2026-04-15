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
import net.crystopia.crystalshard.common.log.reset
import net.crystopia.crystalshard.paper.core.crystalshard
import net.crystopia.crystalshard.paper.custom.smart.SmartEvents
import net.crystopia.crystalshard.paper.folia.threadedTask
import net.crystopia.crystalshard.tests.paper.config.TestConfig
import net.crystopia.crystalshard.tests.paper.databse.entities.UserEntity
import net.crystopia.crystalshard.tests.paper.databse.models.users
import net.crystopia.crystalshard.tests.paper.tests.EventTest
import net.crystopia.crystalshard.tests.paper.tests.base.TestCommand
import org.bukkit.plugin.java.JavaPlugin
import org.ktorm.entity.add
import java.io.File

object CrystalShardPluginTest : JavaPlugin() {

    val instance: CrystalShardPluginTest = this

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

        server.broadcast(cmpb("<red>ERROR</red>").build())
        val cmp = cmpb("<red>ERROR</red>").text("<blue>ERROR</blue>").text("text", "font")


        println("Ohh this is a config value \"${testConfig.data.consoleMessage ?: "No Data"}\"".red() + "GREEN".green())
        testConfig.data.consoleMessage = "UPDATE"
        testConfig.save(testConfig.data)
        println("Ohh this is a config value \"${testConfig.data.consoleMessage ?: "No Data"}\"")

        EventTest
        EventTest.recipe
        TestCommand
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