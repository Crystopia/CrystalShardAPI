package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundSignUpdatePacket for sign edits by the player.
 */
object ServerboundSignUpdatePacketUtil {

    data class SignUpdateEvent(
        var x: Int,
        var y: Int,
        var z: Int,
        var lines: MutableList<String>,
        var isFrontText: Boolean
    )

    /**
     * Attach the Event to the Player.
     */
    fun attach(
        name: String,
        plugin: JavaPlugin,
        player: Player,
        callback: SignUpdateEvent.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundSignUpdatePacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundSignUpdatePacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    plugin.server.scheduler.runTaskLater(
                        plugin,
                        Runnable {
                            callback(
                                SignUpdateEvent(
                                    x = msg.pos.x,
                                    y = msg.pos.y,
                                    z = msg.pos.z,
                                    lines = msg.lines.toMutableList(),
                                    isFrontText = msg.isFrontText
                                )
                            )
                        },
                        1L
                    )
                }
            })

        return true
    }
}