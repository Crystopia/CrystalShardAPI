package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.kyori.adventure.key.Key
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
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
        var id: Byte, var type: PayloadType
    )

    data class PayloadType(
        var prettyName: String, var name: String, var data: MutableMap<String, Any>
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

                            var data: MutableMap<String, Any> = mutableMapOf()
                            parseClickData(msg.payload.get(), data)

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

    private fun parseClickData(
        tag: Tag,
        data: MutableMap<String, Any> = mutableMapOf()
    ) {
        when (tag.id) {
            Tag.TAG_COMPOUND -> {
                if (!tag.asCompound().isEmpty) {
                    val tag = tag.asCompound().get()
                    tag.forEach { string, tag ->
                        if (tag.type.name == CompoundTag.TYPE.name) {
                            val compound: MutableMap<String, Any> = mutableMapOf()
                            data[string] = compound
                            parseClickData(tag, compound)
                        } else {
                            data[string] = tag
                        }

                    }
                }
            }

            Tag.TAG_FLOAT -> {
                if (!tag.asFloat().isEmpty)
                    data["FLOAT"] = tag.asFloat().get().toString()
            }

            Tag.TAG_SHORT -> {
                if (!tag.asShort().isEmpty)
                    data["SHORT"] = tag.asShort().get().toString()
            }

            Tag.TAG_DOUBLE -> {
                if (!tag.asDouble().isEmpty)
                    data["DOUBLE"] = tag.asDouble().get().toString()
            }

            Tag.TAG_STRING -> {
                if (!tag.asString().isEmpty)
                    data["STRING"] = tag.asString().get()
            }

            Tag.TAG_BYTE -> {
                if (!tag.asByte().isEmpty)
                    data["STRING"] = tag.asByte().get().toString()
            }

            Tag.TAG_BYTE_ARRAY -> {
                if (!tag.asByteArray().isEmpty)
                    data["BYTE_ARRAY"] = tag.asByteArray().get().toString()
            }

            Tag.TAG_END -> {
                data["END"] = "null"
            }

            Tag.TAG_INT -> {
                if (!tag.asInt().isEmpty)
                    data["INT"] = tag.asInt().get().toString()
            }

            Tag.TAG_INT_ARRAY -> {
                if (!tag.asIntArray().isEmpty)
                    data["INT_ARRAY"] = tag.asIntArray().get().toString()
            }

            Tag.TAG_LONG -> {
                if (!tag.asLong().isEmpty)
                    data["LONG"] = tag.asLong().get().toString()
            }

            Tag.TAG_LONG_ARRAY -> {
                if (!tag.asLongArray().isEmpty)
                    data["LONG_ARRAY"] = tag.asLongArray().get().toString()
            }
        }
    }
}