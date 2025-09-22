package net.crystopia.crystalshard

import gg.flyte.twilight.Twilight
import gg.flyte.twilight.twilight
import net.crystopia.crystalshard.database.DatabaseManager
import net.crystopia.crystalshard.entities.CrystalEntity
import net.crystopia.crystalshard.entities.CrystalPlayer
import net.crystopia.crystalshard.worlds.CrystalWorld
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.*



/**
 * CrystalShard API v.{version}
 * 
 * Welcome to CrystalShard! Currently, the API is in beta.
 * We will add more Features in the future to make many things easy.
 * 
 */
object CrystalShard {
    
    fun twilightInit(plugin: JavaPlugin, twilightCallBack: Twilight.() -> Unit = {}) {
        val twilight = twilight(plugin)
        twilightCallBack(twilight)
    }

    fun getWorld(name: String): CrystalWorld? {
        val world = Bukkit.getWorld(name) ?: return null
        return CrystalWorld(world)
    }

    fun getEntity(uuid: UUID): CrystalEntity? {
        val entity = Bukkit.getEntity(uuid) ?: return null
        return CrystalEntity(entity)
    }

    fun getPlayer(uuid: UUID): CrystalPlayer? {
        val player = Bukkit.getPlayer(uuid) ?: return null
        return CrystalPlayer(player)
    }


}