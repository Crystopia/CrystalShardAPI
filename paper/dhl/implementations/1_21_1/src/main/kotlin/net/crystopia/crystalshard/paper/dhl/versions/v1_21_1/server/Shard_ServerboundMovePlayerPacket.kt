package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.MovePlayerEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer

class Shard_ServerboundMovePlayerPacket : IServerPacket<MovePlayerEvent> {

    override fun attach(
        data: Shard_ServerPacketData, callback: MovePlayerEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as CraftPlayer).handle
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
                    out.add(msg)

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin, Runnable {
                            callback(
                                MovePlayerEvent(
                                    x = msg.x,
                                    y = msg.y,
                                    z = msg.z,
                                    yRot = msg.yRot,
                                    xRot = msg.xRot,
                                    onGround = msg.isOnGround,
                                    horizontalCollision = null,
                                    hasPos = msg.hasPos,
                                    hasRot = msg.hasRot
                                )
                            )
                        }, 1L
                    )
                }
            })
    }
}