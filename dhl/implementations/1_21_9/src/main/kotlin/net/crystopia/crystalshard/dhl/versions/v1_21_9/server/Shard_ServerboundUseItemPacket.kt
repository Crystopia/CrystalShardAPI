package net.crystopia.crystalshard.dhl.versions.v1_21_9.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.dhl.shared.data.packets.server.UseItemEvent
import net.crystopia.crystalshard.dhl.shared.enums.server.InteractionHand
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import net.minecraft.server.level.ServerPlayer

class Shard_ServerboundUseItemPacket: IServerPacket<UseItemEvent> {


    override fun attach(data: Shard_ServerPacketData, callback: UseItemEvent.() -> Unit) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundUseItemPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundUseItemPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(
                        UseItemEvent(
                            hand = InteractionHand.interactionHand(msg.hand)!!,
                            sequence = msg.sequence,
                            yRot = msg.yRot,
                            xRot = msg.xRot,
                            timestamp = msg.timestamp
                        )
                    )
                }
            })
    }
}