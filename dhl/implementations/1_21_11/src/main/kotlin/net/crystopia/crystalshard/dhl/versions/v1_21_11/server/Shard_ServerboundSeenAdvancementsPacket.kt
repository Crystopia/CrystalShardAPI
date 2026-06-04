package net.crystopia.crystalshard.dhl.versions.v1_21_11.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.shared.ServerPacket
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.packets.server.SeenAdvancementsEvent
import net.crystopia.crystalshard.dhl.shared.enums.packets.SelectAdvancementTabAction
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundSeenAdvancementsPacket
import net.minecraft.server.level.ServerPlayer

class Shard_ServerboundSeenAdvancementsPacket : IServerPacket<SeenAdvancementsEvent> {


    override fun onEvent(data: ServerPacket, callback: (data: SeenAdvancementsEvent) -> Unit) {
        val serverPlayer = (data.player as ServerPlayer)
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder",
            "${data.name.namespace}_${data.name.key}",
            object : MessageToMessageDecoder<ServerboundSeenAdvancementsPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundSeenAdvancementsPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    callback(
                        SeenAdvancementsEvent(
                            action = SelectAdvancementTabAction.valueOf(msg.action.name),
                            tab = NamespacedKey(
                                namespace = msg.tab?.namespace ?: "",
                                key = msg.tab?.path ?: ""
                            )
                        )
                    )
                }
            })
    }
}