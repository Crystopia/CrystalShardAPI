package net.crystopia.crystalshard.tests.paper

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import net.crystopia.crystalshard.paper.core.crystalshard
import net.crystopia.crystalshard.paper.core.utils.Log
import net.crystopia.crystalshard.paper.custom.advancements.Advancement
import net.crystopia.crystalshard.paper.custom.smart.SmartEvents
import net.crystopia.crystalshard.tests.paper.tests.base.TestCommand
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

        TestCommand
        server.pluginManager.registerEvents(SmartEvents, this)
        Log.info("Plugin loaded!")
    }

    override fun onDisable() {

    }
}