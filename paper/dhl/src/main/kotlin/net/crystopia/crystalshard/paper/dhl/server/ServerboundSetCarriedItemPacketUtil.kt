package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundSetCarriedItemPacket for the carried item.
 */
object ServerboundSetCarriedItemPacketUtil {

    data class UseItemEvent(
        var slot: Int
    )

    /**
     * Attach the Event to the Player.
     */
    fun attach(
        name: String, plugin: JavaPlugin, player: Player, callback: UseItemEvent.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundSetCarriedItemPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundSetCarriedItemPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    plugin.server.scheduler.runTaskLater(
                        plugin, Runnable {
                            callback(
                                UseItemEvent(
                                    slot = msg.slot
                                )
                            )
                        }, 1L
                    )
                }
            })

        return true
    }
}