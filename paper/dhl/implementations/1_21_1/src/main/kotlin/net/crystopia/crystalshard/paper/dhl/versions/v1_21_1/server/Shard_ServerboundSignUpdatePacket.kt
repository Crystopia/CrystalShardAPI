package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.SignUpdateEvent
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacketBuilder
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Shard_ServerboundSignUpdatePacket : IServerPacket<SignUpdateEvent> {

    override fun attach(
        data: Shard_ServerPacketData,
        callback: SignUpdateEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundSignUpdatePacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundSignUpdatePacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin ,
                        Runnable {
                            callback(
                                SignUpdateEvent(
                                    x = msg.pos.x,
                                    y = msg.pos.y,
                                    z = msg.pos.z,
                                    lines = msg.lines.toMutableList(),
                                    isFrontText = msg.isFrontText
                                )
                            )
                        },
                        1L
                    )
                }
            })
    }
}