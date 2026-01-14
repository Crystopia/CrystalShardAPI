package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin


/**
 * Util class for attaching and working with the ServerboundInteractPacket for user-defined interactions by the player.
 */
object ServerboundInteractPacketUtil {

    /**
     * Custom Click Type parser for the ServerboundInteractPacket
     */
    enum class ClickActionType(name: String) {

        RIGHT_CLICK("rightClick"), LEFT_CLICK("leftClick"), SHIFT_RIGHT_CLICK("shiftRightClick"), SHIFT_LEFT_CLICK("shiftLeftClick"), UNKNOWN(
            "UNKNOWN"
        );

        companion object {
            fun clickType(isAttack: Boolean, isUsingSecondaryAction: Boolean): ClickActionType {
                return if (isAttack && isUsingSecondaryAction) SHIFT_RIGHT_CLICK
                else if (isAttack) RIGHT_CLICK
                else if (isUsingSecondaryAction) SHIFT_LEFT_CLICK
                else LEFT_CLICK
            }
        }
    }

    /**
     * Attach the Event to the Player.
     */
    fun attach(
        name: String,
        plugin: JavaPlugin,
        player: Player,
        callback: (clickType: ClickActionType, msg: ServerboundInteractPacket) -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundInteractPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundInteractPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    plugin.server.scheduler.runTaskLater(
                        plugin,
                        Runnable {
                            callback(ClickActionType.clickType(msg.isAttack, msg.isUsingSecondaryAction), msg)
                        },
                        1L
                    )
                }
            })

        return true
    }
}