package net.crystopia.crystalshard

import net.crystopia.crystalshard.config.Config
import java.io.File

/**
 * CrystalShard API v.{version}
 * 
 * Welcome to CrystalShard! Currently, the API is in beta.
 * We will add more Features in the future to make many things easy.
 * This is the main Class to interact with the API.
 * 
 */

data class Test(
    val test: String = "Test",
)

object CrystalShard {

    fun test() {
        val config = Config(File("plugin/test/ttt.json"), Test::class.java)

        config.load

        config.save()
        config.reload()
    }
    
}