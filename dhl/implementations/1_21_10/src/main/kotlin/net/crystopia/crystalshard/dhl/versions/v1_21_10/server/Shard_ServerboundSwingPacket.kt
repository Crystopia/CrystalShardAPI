package net.crystopia.crystalshard.dhl.versions.v1_21_10.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.ServerPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.server.SwingArmEvent
import net.crystopia.crystalshard.dhl.shared.enums.server.InteractionHand
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundSwingPacket
import net.minecraft.server.level.ServerPlayer

class Shard_ServerboundSwingPacket : IServerPacket<SwingArmEvent> {

    override fun onEvent(
        data: ServerPacket,
        callback: SwingArmEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundSwingPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundSwingPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(
                        SwingArmEvent(
                            hand = InteractionHand.interactionHand(msg.hand)!!
                        )
                    )
                }
            })
    }
}