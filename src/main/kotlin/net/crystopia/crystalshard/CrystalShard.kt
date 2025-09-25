package net.crystopia.crystalshard

import kotlinx.serialization.Serializable
import net.crystopia.crystalshard.config.Config
import net.crystopia.crystalshard.config.ConfigManager
import net.crystopia.crystalshard.config.ConfigType
import org.bukkit.NamespacedKey
import java.io.File


@Serializable
data class Test(
    val test: String = "Test",
)

/**
 * CrystalShard API v.{version}
 *
 * Welcome to CrystalShard! Currently, the API is in beta.
 * We will add more Features in the future to make many things easy.
 * This is the main Class to interact with the API.
 *
 */

object CrystalShard {

    fun test() {
        // Setup Configs#
        val defaultConf = Config(
            file = File("plugins/Test/config.json"),
            type = ConfigType.JSON
        )
        defaultConf.load(Test())
        defaultConf.data.test

        // with Manager
        val managedConf = ConfigManager.load(
            "testConfig", Test(),
            type = ConfigType.YAML,
            file = File("plugins/Test/config.yml")
        )
    }

}