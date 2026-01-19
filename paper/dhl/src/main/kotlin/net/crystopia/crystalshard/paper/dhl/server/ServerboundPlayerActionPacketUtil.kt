package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundPlayerActionPacket for user-defined action from the player.
 */
object ServerboundPlayerActionPacketUtil {

    /**
     * Attach the Event to the Player.
     */
    fun attach(
        name: String,
        plugin: JavaPlugin,
        player: Player,
        callback: Any.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundPlayerActionPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundPlayerActionPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                  // TODO
                    plugin.server.scheduler.runTaskLater(
                        plugin,
                        Runnable {
                            callback(msg)
                        },
                        1L
                    )
                }
            })

        return true
    }
}