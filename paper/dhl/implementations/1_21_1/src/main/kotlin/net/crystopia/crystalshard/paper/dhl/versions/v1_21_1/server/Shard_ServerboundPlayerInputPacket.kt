package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.PlayerInputEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundPlayerInputPacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer

class Shard_ServerboundPlayerInputPacket : IServerPacket<PlayerInputEvent> {

    override fun attach(
        data: Shard_ServerPacketData, callback: PlayerInputEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder",
            "${data.name.namespace}_${data.name.key}",
            object : MessageToMessageDecoder<ServerboundPlayerInputPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundPlayerInputPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin, Runnable {
                            callback(
                                PlayerInputEvent(
                                    forward = msg.zza > 0,
                                    backward = msg.zza < 0,
                                    left = msg.xxa > 0,
                                    right = msg.xxa < 0,
                                    jump = msg.isJumping,
                                    shift = msg.isShiftKeyDown,
                                    sprint = msg.isShiftKeyDown
                                )
                            )
                        }, 1L
                    )
                }
            })

    }
}