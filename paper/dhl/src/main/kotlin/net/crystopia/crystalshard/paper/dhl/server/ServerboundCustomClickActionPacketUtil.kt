package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundCustomClickActionPacket for user-defined clicks by the player.
 */
object ServerboundCustomClickActionPacketUtil {

    /**
     * Attach the Event to the Player.
     */
    fun attach(
        name: String,
        plugin: JavaPlugin,
        player: Player,
        callback: ServerboundCustomClickActionPacket.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundCustomClickActionPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundCustomClickActionPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

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