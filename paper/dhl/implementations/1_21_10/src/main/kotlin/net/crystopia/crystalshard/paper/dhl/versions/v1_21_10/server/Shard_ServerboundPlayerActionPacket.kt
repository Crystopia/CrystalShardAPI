package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.PlayerActionEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.Action
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.Direction
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Shard_ServerboundPlayerActionPacket : IServerPacket<PlayerActionEvent>{

    override fun attach(
        data: Shard_ServerPacketData,
        callback: PlayerActionEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as CraftPlayer).handle
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

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin,
                        Runnable {
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
                        },
                        1L
                    )
                }
            })
    }
}