package net.crystopia.crystalshardtest

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import gg.flyte.twilight.twilight
import net.crystopia.crystalshard.common.CrystalShard
import net.crystopia.crystalshard.common.custom.CrystalEvents
import net.crystopia.crystalshardtest.events.PlayerJoin
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    companion object {
        lateinit var instance: Main
    }

    init {
        instance = this
    }
    
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIPaperConfig(this).silentLogs(true))
        CrystalShard.init(this)
        
    }
    
    override fun onEnable() {
        CommandAPI.onEnable();
        val twilight = twilight(this)

        TestyCommand
        server.pluginManager.registerEvents(PlayerJoin, this)
        server.pluginManager.registerEvents(CrystalEvents, this)

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