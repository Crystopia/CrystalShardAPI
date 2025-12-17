package net.crystopia.crystalshardtest

import net.crystopia.crystalshard.CrystalShard
import net.crystopia.crystalshardtest.events.PlayerJoin
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    /*
    setEntityDataPacket(
                player,
                12321,
                mutableListOf(
                    SynchedEntityData.DataValue.create(net.minecraft.world.entity.player.Player.DATA_PLAYER_MODE_CUSTOMISATION,
                        (0x01 or 0x02 or 0x04 or 0x08 or 0x10 or 0x20 or 0x40).toByte())

                )
            )
     */
    
    override fun onLoad() {
        
        CrystalShard.init(this)
        
    }
    
    override fun onEnable() {

        server.pluginManager.registerEvents(PlayerJoin, this)
        
    }

    override fun onDisable() {
        
    }
    
}