package net.crystopia.crystalshard.dhl.versions.v1_21_11.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.data.packets.server.PlayerActionEvent
import net.crystopia.crystalshard.dhl.shared.ServerPacket
import net.crystopia.crystalshard.dhl.shared.enums.server.Action
import net.crystopia.crystalshard.dhl.shared.enums.server.Direction
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import net.minecraft.server.level.ServerPlayer

class Shard_ServerboundPlayerActionPacket : IServerPacket<PlayerActionEvent>{

    override fun onEvent(
        data: ServerPacket,
        callback: PlayerActionEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundPlayerActionPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundPlayerActionPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(
                        PlayerActionEvent(
                            x = msg.pos.x,
                            y = msg.pos.y,
                            z = msg.pos.z,
                            direction = Direction.direction(msg.direction),
                            action = Action.action(msg.action.name)!!,
                            sequence = msg.sequence
                        )
                    )
                }
            })
    }
}