package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.UseItemEvent
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.InteractionHand
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundUseItemPacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer


class Shard_ServerboundUseItemPacket : IServerPacket<UseItemEvent> {


    override fun attach(data: Shard_ServerPacketData, callback: UseItemEvent.() -> Unit) {
        val serverPlayer = (data.player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder",
            "${data.name.namespace}_${data.name.key}",
            object : MessageToMessageDecoder<ServerboundUseItemPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundUseItemPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin,
                        Runnable {
                            callback(
                                UseItemEvent(
                                    hand = InteractionHand.interactionHand(msg.hand)!!,
                                    sequence = msg.sequence,
                                    yRot = null,
                                    xRot = null,
                                    timestamp = msg.timestamp
                                )
                            )
                        },
                        1L
                    )
                }
            })
    }
}