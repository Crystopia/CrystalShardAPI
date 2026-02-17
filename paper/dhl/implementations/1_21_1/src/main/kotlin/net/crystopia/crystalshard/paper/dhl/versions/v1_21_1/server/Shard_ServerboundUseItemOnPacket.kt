package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.BlockHitResult
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.UseItemOnEvent
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.Direction
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.InteractionHand
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ItemUseType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin


class Shard_ServerboundUseItemOnPacket: IServerPacket<UseItemOnEvent> {

    override fun attach(
        data: Shard_ServerPacketData,
        callback: UseItemOnEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundUseItemOnPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundUseItemOnPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin,
                        Runnable {
                            callback(
                                UseItemOnEvent(
                                    blockHit = BlockHitResult(
                                        direction = Direction.direction(msg.hitResult.direction),
                                        x = msg.hitResult.blockPos.x,
                                        y = msg.hitResult.blockPos.y,
                                        z = msg.hitResult.blockPos.z,
                                        type = ItemUseType.itemUseType(msg.hitResult.type)!!,
                                        inside = msg.hitResult.isInside,
                                        worldBorderHit = null
                                    ),
                                    hand = InteractionHand.interactionHand(msg.hand)!!,
                                    sequence = msg.sequence,
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