package net.crystopia.crystalshard.dhl.versions.v1_21_10.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.data.packets.server.SetCarriedItemEvent
import net.crystopia.crystalshard.dhl.shared.Shard_ServerPacket
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
import net.minecraft.server.level.ServerPlayer

class Shard_ServerboundSetCarriedItemPacket: IServerPacket<SetCarriedItemEvent> {

    override fun onEvent(
        data: Shard_ServerPacket,
        callback: SetCarriedItemEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundSetCarriedItemPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundSetCarriedItemPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(
                        SetCarriedItemEvent(
                            slot = msg.slot
                        )
                    )
                }
            })
    }
}