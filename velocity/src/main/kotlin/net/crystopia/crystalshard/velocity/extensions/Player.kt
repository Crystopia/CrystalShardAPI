package net.crystopia.crystalshard.velocity.extensions

import com.velocitypowered.api.proxy.ConnectionRequestBuilder
import com.velocitypowered.api.proxy.Player

fun Player.connect(server: String, callback: ConnectionRequestBuilder.() -> Unit): Player {
    val req = createConnectionRequest(net.crystopia.crystalshard.velocity.proxy.getServer(server).get())
    callback(req)
    req.fireAndForget()
    return this
}