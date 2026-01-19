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

    data class PlayerActionEvent(
        var x: Int,
        var y: Int,
        var z: Int,
        var direction: Direction,
        var action: Action,
        var sequence: Int
    )

    enum class Direction(val id: net.minecraft.core.Direction) {
        DOWN(net.minecraft.core.Direction.DOWN),
        UP(net.minecraft.core.Direction.UP),
        NORTH(net.minecraft.core.Direction.NORTH),
        SOUTH(net.minecraft.core.Direction.SOUTH),
        WEST(net.minecraft.core.Direction.WEST),
        EAST(net.minecraft.core.Direction.EAST);

        companion object {
            val entries = Direction.entries

            fun direction(id: net.minecraft.core.Direction): Direction? {
                entries.forEach { direction ->
                    if (direction.id == id)
                        return direction
                }
                return null
            }
        }
    }

    enum class Action {
        START_DESTROY_BLOCK,
        ABORT_DESTROY_BLOCK,
        STOP_DESTROY_BLOCK,
        DROP_ALL_ITEMS,
        DROP_ITEM,
        RELEASE_USE_ITEM,
        SWAP_ITEM_WITH_OFFHAND;

        companion object {
            val entries = Action.entries

            fun action(id: String): Action? {
                entries.forEach { direction ->
                    if (direction.name.equals(id, ignoreCase = true))
                        return direction
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
        callback: PlayerActionEvent.() -> Unit
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

                    plugin.server.scheduler.runTaskLater(
                        plugin,
                        Runnable {
                            callback(
                                PlayerActionEvent(
                                    x = msg.pos.x,
                                    y = msg.pos.y,
                                    z = msg.pos.z,
                                    direction = Direction.direction(msg.direction)!!,
                                    action = Action.action(msg.action.name)!!,
                                    sequence = msg.sequence
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