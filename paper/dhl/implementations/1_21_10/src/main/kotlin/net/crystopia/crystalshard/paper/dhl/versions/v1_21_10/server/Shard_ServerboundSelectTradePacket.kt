package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.SelectTradeEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundSelectTradePacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer


class Shard_ServerboundSelectTradePacket : IServerPacket<SelectTradeEvent> {

    override fun attach(
        data: Shard_ServerPacketData,
        callback: SelectTradeEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder",
            "${data.name.namespace}_${data.name.key}",
            object : MessageToMessageDecoder<ServerboundSelectTradePacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundSelectTradePacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin,
                        Runnable {
                            callback(
                                SelectTradeEvent(
                                    item = msg.item
                                )
                            )
                        },
                        1L
                    )
                }
            })
    }
}