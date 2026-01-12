package net.crystopia.crystalshard.velocity

import com.velocitypowered.api.proxy.ProxyServer


var proxy: ProxyServer
    get() {

        require(proxy != null) {
            "Please init CrystalShard first. Use crystalshard()"
        }

        return proxy
    }
    internal set(value) {
        proxy = value
    }

fun crystalshard(proxy: ProxyServer) {
    net.crystopia.crystalshard.velocity.proxy = proxy
}