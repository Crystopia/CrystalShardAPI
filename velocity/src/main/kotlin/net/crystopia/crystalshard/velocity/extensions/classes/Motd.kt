package net.crystopia.crystalshard.velocity.extensions.classes

import com.velocitypowered.api.event.proxy.ProxyPingEvent
import com.velocitypowered.api.proxy.server.ServerPing
import com.velocitypowered.api.util.Favicon
import com.velocitypowered.api.util.ModInfo
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import javax.imageio.ImageIO
import kotlin.io.encoding.Base64


class Motd(val event: ProxyPingEvent) {

    val ping = ServerPing.builder()

    fun icon(url: String) {

        val url = URL(url)
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            val image = ImageIO.read(inputStream)
            ping.favicon(
                Favicon.create(image)
            )
        }
    }

    fun currentPlayers(count: Int) {
        ping.onlinePlayers(count)
    }

    fun maxPlayers(maxPlayers: Int) {
        ping.maximumPlayers(maxPlayers)
    }

    fun description(description: Component) {
        ping.description(description)
    }

    fun hover(hover: Component) {
        ping.samplePlayers(
            ServerPing.SamplePlayer(
                LegacyComponentSerializer.builder().build().serialize(hover), UUID.randomUUID()
            )
        )
    }

    fun modInfo(info: ModInfo) {
        ping.mods(info)
    }

    fun version(component: Component) {
        ping.version(ServerPing.Version(0, PlainTextComponentSerializer.builder().build().serialize(component)))
    }

    fun build() {
        event.ping = ping.build()
    }
}