package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.Entity
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityInteractEvent
import org.bukkit.event.inventory.InventoryInteractEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin


/**
 * Util class for attaching and working with the ServerboundInteractPacket for user-defined interactions by the player.
 */
object ServerboundInteractPacketUtil {

    data class InteractEvent(
        var entityId: Int,
        var isAttack: Boolean,
        var sneakKeyPressed: Boolean,
        var clickActionType: ClickActionType
    )

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
        callback: InteractEvent.() -> Unit
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
                            callback(
                                InteractEvent(
                                    entityId = msg.entityId,
                                    isAttack = msg.isAttack,
                                    sneakKeyPressed = msg.isUsingSecondaryAction,
                                    clickActionType = ClickActionType.clickType(msg.isAttack, msg.isUsingSecondaryAction)
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