package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundPlayerCommandPacket for user-defined commands send by the player.
 */
object ServerboundPlayerCommandPacketUtil {

    data class PlayerCommandEvent(
        var entityId: Int,
        var action: CommandAction,
        var data: Int
    )

    enum class CommandAction(val id: ServerboundPlayerCommandPacket.Action) {
        STOP_SLEEPING(ServerboundPlayerCommandPacket.Action.STOP_SLEEPING),
        START_SPRINTING(ServerboundPlayerCommandPacket.Action.START_SPRINTING),
        STOP_SPRINTING(ServerboundPlayerCommandPacket.Action.STOP_SPRINTING),
        START_RIDING_JUMP(ServerboundPlayerCommandPacket.Action.START_RIDING_JUMP),
        STOP_RIDING_JUMP(ServerboundPlayerCommandPacket.Action.STOP_RIDING_JUMP),
        OPEN_INVENTORY(ServerboundPlayerCommandPacket.Action.OPEN_INVENTORY),
        START_FALL_FLYING(ServerboundPlayerCommandPacket.Action.START_FALL_FLYING);

        companion object {
            var entries = CommandAction.entries

            fun commandAction(id: ServerboundPlayerCommandPacket.Action): CommandAction? {
                entries.forEach { commandAction ->
                    if (commandAction.id == id) {
                        return commandAction
                    }
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
        callback: PlayerCommandEvent.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundPlayerCommandPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundPlayerCommandPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    plugin.server.scheduler.runTaskLater(
                        plugin,
                        Runnable {
                            callback(
                                PlayerCommandEvent(
                                    entityId = msg.id,
                                    action = CommandAction.commandAction(msg.action)!!,
                                    data = msg.data
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