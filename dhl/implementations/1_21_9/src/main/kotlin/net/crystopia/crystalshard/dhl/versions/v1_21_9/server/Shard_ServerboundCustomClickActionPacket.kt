package net.crystopia.crystalshard.dhl.versions.v1_21_9.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.packets.server.CustomClickEvent
import net.crystopia.crystalshard.dhl.shared.data.packets.server.Payload
import net.crystopia.crystalshard.dhl.shared.data.packets.server.PayloadType
import net.crystopia.crystalshard.dhl.shared.Shard_ServerPacket
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.nbt.serialize
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import net.minecraft.server.level.ServerPlayer

/**
 * Util class for attaching and working with the ServerboundCustomClickActionPacket for user-defined clicks by the player.
 */
class Shard_ServerboundCustomClickActionPacket : IServerPacket<CustomClickEvent>  {
    override fun onEvent(
        data: Shard_ServerPacket,
        callback: CustomClickEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[data.name.toString()] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", data.name.toString(), object : MessageToMessageDecoder<ServerboundCustomClickActionPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundCustomClickActionPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    var data: MutableMap<String, Any> = mutableMapOf()
                    CompoundTag().serialize(msg.payload.get(), data)

                    callback(
                        CustomClickEvent(
                            key = NamespacedKey(msg.id.namespace, msg.id.path), payload = Payload(
                                id = msg.payload.get().id, type = PayloadType(
                                    prettyName = msg.payload.get().type.prettyName,
                                    name = msg.payload.get().type.name,
                                    data = data
                                )
                            )
                        )
                    )
                }
            })
    }

}