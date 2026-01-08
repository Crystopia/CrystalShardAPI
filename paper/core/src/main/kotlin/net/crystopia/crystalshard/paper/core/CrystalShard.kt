package net.crystopia.crystalshard.paper.core

import org.bukkit.plugin.java.JavaPlugin

/**
 * CrystalShard API
 *
 * Welcome to CrystalShard! Currently, the API is in beta.
 * We will add more Features in the future to make many things easy.
 * This is the main Class to interact with the API.
 *
 */

object CrystalShard {

    lateinit var plugin: JavaPlugin

    /**
     * Init Function to set up CrystalShard easily...
     */
    fun init(plugin: JavaPlugin) {
        this.plugin = plugin
    }
}