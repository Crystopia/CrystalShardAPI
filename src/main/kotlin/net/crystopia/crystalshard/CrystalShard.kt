package net.crystopia.crystalshard

import net.crystopia.crystalshard.entities.CrystalEntity
import net.crystopia.crystalshard.entities.CrystalPlayer
import net.crystopia.crystalshard.worlds.CrystalWorld
import org.bukkit.Bukkit
import java.util.*


/**
 * CrystalShard API v.{version}
 * 
 * Welcome to CrystalShard! Currently, the API is in beta.
 * We will add more Features in the future to make many things easy.
 * This is the main Class to interact with the API.
 * 
 */
object CrystalShard {


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