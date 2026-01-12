package net.crystopia.crystalshard.velocity.extensions

import com.velocitypowered.api.event.proxy.ProxyPingEvent
import net.crystopia.crystalshard.velocity.extensions.classes.Motd


fun ProxyPingEvent.motd(motd: Motd.() -> Unit): ProxyPingEvent {
    motd(Motd(this))
    return this
}

fun ProxyPingEvent.motd(): Motd {
    val motd = Motd(this)
    return motd
}