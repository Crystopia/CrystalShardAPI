package net.crystopia.crystalshard.dhl.versions.v1_21_10.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.data.packets.server.MovePlayerEvent
import net.crystopia.crystalshard.dhl.shared.ServerPacket
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
import net.minecraft.server.level.ServerPlayer

class Shard_ServerboundMovePlayerPacket : IServerPacket<MovePlayerEvent> {

    override fun onEvent(
        data: ServerPacket,
        callback: MovePlayerEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder",
            "${data.name.namespace}_${data.name.key}",
            object : MessageToMessageDecoder<ServerboundMovePlayerPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundMovePlayerPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(MovePlayerEvent(
                        x = msg.x,
                        y = msg.y,
                        z = msg.z,
                        yRot = msg.yRot,
                        xRot = msg.xRot,
                        onGround = msg.isOnGround,
                        horizontalCollision = msg.horizontalCollision(),
                        hasPos = msg.hasPos,
                        hasRot = msg.hasRot
                    ))
                }
            })
    }
}