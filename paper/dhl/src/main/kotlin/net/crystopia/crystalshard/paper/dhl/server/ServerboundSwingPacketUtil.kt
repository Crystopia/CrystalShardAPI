package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.network.protocol.game.ServerboundSwingPacket
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundContainerButtonClickPacket for user-defined clicks in a GUI by the player.
 */
object ServerboundSwingPacketUtil {

    data class SwingArmEvent(
        var hand: InteractionHand
    )

    enum class InteractionHand(val id: net.minecraft.world.InteractionHand) {
        MAIN_HAND(net.minecraft.world.InteractionHand.MAIN_HAND),
        OFF_HAND(net.minecraft.world.InteractionHand.OFF_HAND);

        companion object {
            var entries = InteractionHand.entries

            fun interactionHand(id: net.minecraft.world.InteractionHand): InteractionHand? {
                entries.forEach { interactionHand ->
                    if (interactionHand.id == id)
                        return interactionHand
                }
                return null
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
        callback: SwingArmEvent.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundSwingPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundSwingPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    plugin.server.scheduler.runTaskLater(
                        plugin,
                        Runnable {
                            callback(
                                SwingArmEvent(
                                    hand = InteractionHand.interactionHand(msg.hand)!!
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