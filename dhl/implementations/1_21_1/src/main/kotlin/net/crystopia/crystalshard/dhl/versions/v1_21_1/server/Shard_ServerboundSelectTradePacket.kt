package net.crystopia.crystalshard.dhl.versions.v1_21_1.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.data.packets.server.SelectTradeEvent
import net.crystopia.crystalshard.dhl.shared.Shard_ServerPacket
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundSelectTradePacket
import net.minecraft.server.level.ServerPlayer


class Shard_ServerboundSelectTradePacket : IServerPacket<SelectTradeEvent> {

    override fun onEvent(
        data: Shard_ServerPacket,
        callback: SelectTradeEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder",
            "${data.name.namespace}_${data.name.key}",
            object : MessageToMessageDecoder<ServerboundSelectTradePacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundSelectTradePacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(
                        SelectTradeEvent(
                            item = msg.item
                        )
                    )
                }
            })
    }
}