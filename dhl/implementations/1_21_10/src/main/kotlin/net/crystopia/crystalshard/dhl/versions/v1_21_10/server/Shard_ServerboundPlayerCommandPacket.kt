package net.crystopia.crystalshard.dhl.versions.v1_21_10.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.data.packets.server.PlayerCommandEvent
import net.crystopia.crystalshard.dhl.shared.ServerPacket
import net.crystopia.crystalshard.dhl.shared.enums.server.CommandAction
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket
import net.minecraft.server.level.ServerPlayer

class Shard_ServerboundPlayerCommandPacket : IServerPacket<PlayerCommandEvent> {

    override fun onEvent(
        data: ServerPacket,
        callback: PlayerCommandEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder",
            "${data.name.namespace}_${data.name.key}",
            object : MessageToMessageDecoder<ServerboundPlayerCommandPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundPlayerCommandPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(
                        PlayerCommandEvent(
                            entityId = msg.id,
                            action = CommandAction.commandAction(msg.action)!!,
                            data = msg.data
                        )
                    )
                }
            })
    }
}