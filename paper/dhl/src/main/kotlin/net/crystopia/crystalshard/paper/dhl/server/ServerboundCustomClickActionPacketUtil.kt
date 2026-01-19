package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import io.papermc.paper.event.player.PlayerCustomClickEvent
import kotlinx.serialization.json.Json
import net.kyori.adventure.key.Key
import net.minecraft.nbt.StringTag
import net.minecraft.nbt.Tag
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundCustomClickActionPacket for user-defined clicks by the player.
 */
object ServerboundCustomClickActionPacketUtil {

    data class CustomClickEvent(
        var key: Key, var payload: Payload
    )

    data class Payload(
        var id: Byte, var type: PayloadType<*>
    )

    data class PayloadType<T : Any>(
        var prettyName: String, var name: String, var data: T
    )

    /**
     * Attach the Event to the Player.
     */
    fun attach(
        name: String, plugin: JavaPlugin, player: Player, callback: CustomClickEvent.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundCustomClickActionPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundCustomClickActionPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    plugin.server.scheduler.runTaskLater(
                        plugin, Runnable {

                            var data: Any? = null

                            when (msg.payload.get().id) {
                                Tag.TAG_COMPOUND -> {
                                    data = msg.payload.get().asCompound().get()
                                }

                                else -> data = msg.payload.get()
                            }

                            callback(
                                CustomClickEvent(
                                    key = Key.key(msg.id.namespace, msg.id.path), payload = Payload(
                                        id = msg.payload.get().id, type = PayloadType(
                                            prettyName = msg.payload.get().type.prettyName,
                                            name = msg.payload.get().type.name,
                                            data = data
                                        )
                                    )
                                )
                            )
                        }, 1L
                    )
                }
            })

        return true
    }
}