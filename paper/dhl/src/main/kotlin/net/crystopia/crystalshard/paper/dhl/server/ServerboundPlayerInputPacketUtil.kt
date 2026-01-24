package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.network.protocol.game.ServerboundPlayerInputPacket
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundPlayerInputPacket for user-defined inputs by the player.
 */
object ServerboundPlayerInputPacketUtil {


    data class PlayerInputEvent(
        var forward: Boolean,
        var backward: Boolean,
        var left: Boolean,
        var right: Boolean,
        var jump: Boolean,
        var shift: Boolean,
        var sprint: Boolean
    )


    /**
     * Attach the Event to the Player.
     */
    fun attach(
        name: String,
        plugin: JavaPlugin,
        player: Player,
        callback: PlayerInputEvent.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundPlayerInputPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundPlayerInputPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    plugin.server.scheduler.runTaskLater(
                        plugin,
                        Runnable {
                            callback(
                                PlayerInputEvent(
                                    forward = msg.input.forward,
                                    backward = msg.input.backward,
                                    left = msg.input.left,
                                    right = msg.input.right,
                                    jump = msg.input.jump,
                                    shift = msg.input.shift,
                                    sprint = msg.input.sprint
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