package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.MoveVehicleEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundMoveVehiclePacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Shard_ServerboundMoveVehiclePacket : IServerPacket<MoveVehicleEvent> {

    override fun attach(
        data: Shard_ServerPacketData,
        callback: MoveVehicleEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundMoveVehiclePacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundMoveVehiclePacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin,
                        Runnable {
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
                        },
                        1L
                    )
                }
            })
    }
}