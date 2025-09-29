package net.crystopia.crystalshard

import kotlinx.serialization.Serializable
import net.crystopia.crystalshard.database.RedisDatabaseManager


@Serializable
data class Test(
    val test: String = "Test",
)

/**
 * CrystalShard API
 *
 * Welcome to CrystalShard! Currently, the API is in beta.
 * We will add more Features in the future to make many things easy.
 * This is the main Class to interact with the API.
 *
 */

object CrystalShard {
    

}