package net.crystopia.crystalshard.dhl.versions.v1_21_11.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.data.packets.server.ButtonClickEvent
import net.crystopia.crystalshard.dhl.shared.Shard_ServerPacket
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundContainerButtonClickPacket
import net.minecraft.server.level.ServerPlayer

class Shard_ServerboundContainerButtonClickPacket : IServerPacket<ButtonClickEvent> {


    override fun onEvent(data: Shard_ServerPacket, callback: ButtonClickEvent.() -> Unit) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundContainerButtonClickPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundContainerButtonClickPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(
                        ButtonClickEvent(
                            containerId = msg.containerId,
                            buttonId = msg.buttonId
                        )
                    )
                }
            })
    }
}