package net.crystopia.crystalshard.common.database.custom

import eu.vendeli.rethis.ReThis
import eu.vendeli.rethis.command.generic.del
import eu.vendeli.rethis.command.pubsub.subscribe
import eu.vendeli.rethis.command.string.get
import eu.vendeli.rethis.command.string.set
import eu.vendeli.rethis.shared.types.RType
import eu.vendeli.rethis.types.common.RespVer

class RedisDatabase {

    private var ip: String = "127.0.0.1"
    private var port: Int = 6379
    private var username: String? = null
    private var password: String? = null

    fun init(
        ip: String,
        port: Int,
        username: String,
        password: String,
    ): RedisDatabase {
        this.ip = ip
        this.port = port
        this.username = username
        this.password = password

        return this
    }

    val client = ReThis(
        host = ip,
        port = port,
        protocol = RespVer.V2
    ) {
        if (username != null && password != null) {
            auth(password!!.toCharArray(), username)
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