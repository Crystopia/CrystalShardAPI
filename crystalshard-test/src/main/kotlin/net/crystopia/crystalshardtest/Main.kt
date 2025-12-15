package net.crystopia.crystalshardtest

import net.crystopia.crystalshard.CrystalShard
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    override fun onLoad() {
        
        CrystalShard.init(this)
        
    }
    
    override fun onEnable() {
        
    }

    override fun onDisable() {
        
    }
    
}