package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin


/**
 * Util class for attaching and working with the ServerboundInteractPacket for user-defined interactions by the player.
 */
object ServerboundMovePlayerPacketUtil {

    data class MovePlayerEvent(
        var x: Double,
        var y: Double,
        var z: Double,
        var yRot: Float,
        var xRot: Float,
        var onGround: Boolean,
        var horizontalCollision: Boolean,
        var hasPos: Boolean,
        var hasRot: Boolean
    )

    /**
     * Attach the Event to the Player.
     */
    fun attach(
        name: String, plugin: JavaPlugin, player: Player, callback: MovePlayerEvent.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundMovePlayerPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundMovePlayerPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    plugin.server.scheduler.runTaskLater(
                        plugin, Runnable {
                            callback(MovePlayerEvent(
                                x = msg.x,
                                y = msg.y,
                                z = msg.z,
                                yRot = msg.yRot,
                                xRot = msg.xRot,
                                onGround = msg.isOnGround,
                                horizontalCollision = msg.horizontalCollision(),
                                hasPos = msg.hasPos,
                                hasRot = msg.hasRot
                            ))
                        }, 1L
                    )
                }
            })

        return true
    }
}