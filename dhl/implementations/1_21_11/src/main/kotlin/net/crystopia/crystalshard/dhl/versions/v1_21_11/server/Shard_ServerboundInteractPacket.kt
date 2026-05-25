package net.crystopia.crystalshard.dhl.versions.v1_21_11.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.data.packets.server.InteractEvent
import net.crystopia.crystalshard.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ClickActionType
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import net.minecraft.server.level.ServerPlayer

class Shard_ServerboundInteractPacket : IServerPacket<InteractEvent> {

    override fun attach(
        data: Shard_ServerPacketData,
        callback: InteractEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundInteractPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundInteractPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(
                        InteractEvent(
                            entityId = msg.entityId,
                            isAttack = msg.isAttack,
                            sneakKeyPressed = msg.isUsingSecondaryAction,
                            clickActionType = ClickActionType.clickType(msg.isAttack, msg.isUsingSecondaryAction)
                        )
                    )
                }
            })
    }
}