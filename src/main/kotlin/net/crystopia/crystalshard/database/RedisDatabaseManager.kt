package net.crystopia.crystalshard.database

import eu.vendeli.rethis.ReThis
import eu.vendeli.rethis.command.pubsub.subscribe
import eu.vendeli.rethis.types.common.RespVer

/**
 *
 * Create a redis connection to process Data!
 *
 */
class RedisDatabaseManager(
    ip: String,
    port: Int,
    username: String,
    password: String,
) {

    val client = ReThis(
        host = ip,
        port = port,
        protocol = RespVer.V2
    ) {
        auth(password.toCharArray(), username)
    }

    suspend fun subscribe(name: String): String? {
        try {
            var message: String? = null
            client.subscribe(name) { _, msg ->
                message = msg
            }
            return message
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


}