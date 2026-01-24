package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.network.protocol.game.ServerboundMoveVehiclePacket
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundMoveVehiclePacket for vehicle movements by the Client.
 */
object ServerboundMoveVehiclePacketUtil {


    data class MoveVehicleEvent(
        var x: Double,
        var y: Double,
        var z: Double,
        var yRot: Float,
        var xRot: Float,
        var onGround: Boolean
    )

    /**
     * Attach the Event to the Player.
     */
    fun attach(
        name: String,
        plugin: JavaPlugin,
        player: Player,
        callback: MoveVehicleEvent.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundMoveVehiclePacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundMoveVehiclePacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    plugin.server.scheduler.runTaskLater(
                        plugin,
                        Runnable {
                            callback(
                                MoveVehicleEvent(
                                    x = msg.position.x,
                                    y = msg.position.y,
                                    z = msg.position.z,
                                    yRot = msg.yRot,
                                    xRot = msg.xRot,
                                    onGround = msg.onGround
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