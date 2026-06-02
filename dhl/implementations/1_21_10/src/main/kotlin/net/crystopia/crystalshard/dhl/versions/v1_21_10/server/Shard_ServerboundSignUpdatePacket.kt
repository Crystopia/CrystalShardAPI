package net.crystopia.crystalshard.dhl.versions.v1_21_10.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.Shard_ServerPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.server.SignUpdateEvent
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket
import net.minecraft.server.level.ServerPlayer

class Shard_ServerboundSignUpdatePacket : IServerPacket<SignUpdateEvent> {

    override fun onEvent(
        data: Shard_ServerPacket,
        callback: SignUpdateEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundSignUpdatePacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundSignUpdatePacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(
                        SignUpdateEvent(
                            x = msg.pos.x,
                            y = msg.pos.y,
                            z = msg.pos.z,
                            lines = msg.lines.toMutableList(),
                            isFrontText = msg.isFrontText
                        )
                    )
                }
            })
    }
}