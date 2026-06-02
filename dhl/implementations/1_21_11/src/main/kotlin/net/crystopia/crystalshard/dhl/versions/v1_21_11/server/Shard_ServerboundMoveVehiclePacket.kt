package net.crystopia.crystalshard.dhl.versions.v1_21_11.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.data.packets.server.MoveVehicleEvent
import net.crystopia.crystalshard.dhl.shared.ServerPacket
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundMoveVehiclePacket
import net.minecraft.server.level.ServerPlayer

class Shard_ServerboundMoveVehiclePacket : IServerPacket<MoveVehicleEvent> {

    override fun onEvent(
        data: ServerPacket,
        callback: MoveVehicleEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundMoveVehiclePacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundMoveVehiclePacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(
                        MoveVehicleEvent(
                            x = msg.position.x,
                            y = msg.position.y,
                            z = msg.position.z,
                            yRot = msg.yRot,
                            xRot = msg.xRot,
                            onGround = msg.onGround
                        )
                    )
                }
            })
    }
}