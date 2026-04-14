package net.crystopia.crystalshard.common.database.custom

import eu.vendeli.rethis.ReThis
import eu.vendeli.rethis.command.generic.del
import eu.vendeli.rethis.command.pubsub.subscribe
import eu.vendeli.rethis.command.string.get
import eu.vendeli.rethis.command.string.set
import eu.vendeli.rethis.shared.types.RType
import eu.vendeli.rethis.types.common.RespVer

/**
 * Build a redis Database instance to Connect to a Redis-Database Server.
 */
fun database(
    ip: String,
    port: Int,
    username: String,
    password: String,
    database: RedisDatabase.() -> Unit
): RedisDatabase {
    val db = RedisDatabase(ip, port, username, password)
    database(db)
    return db
}

class RedisDatabase(
    ip: String = "127.0.0.1",
    port: Int = 6379,
    username: String? = null,
    password: String? = null,
) {

    val client = ReThis(
        host = ip,
        port = port,
        protocol = RespVer.V2
    ) {
        if (username != null && password != null) {
            auth(password.toCharArray(), username)
        }
    }

    suspend fun get(key: String): String? {
        return client.get(key)
    }

    suspend fun set(key: String, value: String): String? {
        return client.set(key, value)
    }

    suspend fun delete(key: String): Long {
        return client.del(key)
    }

    suspend fun transaction(block: ReThis.() -> Unit = {}): List<RType>? {
        return client.transaction {
            block(this)
        }
    }

    suspend fun pipeline(block: ReThis.() -> Unit = {}): List<RType> {
        return client.pipeline { block(this) }
    }

    suspend fun subscribe(
        name: String, callback: (message: String) -> Unit = {}
    ): String? {
        try {
            var message: String? = null
            client.subscribe(name) { _, msg ->
                message = msg
            }
            if (message != null) {
                callback(message)
            }
            return message
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}