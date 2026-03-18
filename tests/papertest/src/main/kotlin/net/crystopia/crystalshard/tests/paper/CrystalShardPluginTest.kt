package net.crystopia.crystalshard.tests.paper

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import gg.flyte.twilight.twilight
import net.crystopia.crystalshard.paper.core.crystalshard
import net.crystopia.crystalshard.paper.custom.advancements.Advancement
import net.crystopia.crystalshard.paper.custom.smart.Events
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

    lateinit var adv: Advancement

    override fun onEnable() {
        crystalshard(this)
        twilight(this)
        CommandAPI.onEnable();

        TestCommand
        server.pluginManager.registerEvents(Events(), this)

        /*
                val entity = Bukkit.getWorld("world")!!.spawnEntity(
                    Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0), EntityType.TEXT_DISPLAY
                ) as TextDisplay
        
                entity.text(MiniMessage.miniMessage().deserialize("<red>Hello</red>"))
        
                entity.playerInteractEntityEvent(null, null) {
                    Log.debug("Cool")
                    player.sendMessage(entity.text())
                }                
         */
    }

    override fun onDisable() {

    }
}