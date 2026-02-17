package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.PlayerCommandEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.CommandAction
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer

class Shard_ServerboundPlayerCommandPacket : IServerPacket<PlayerCommandEvent> {

    override fun attach(
        data: Shard_ServerPacketData,
        callback: PlayerCommandEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as CraftPlayer).handle
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

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin,
                        Runnable {
                            callback(
                                PlayerCommandEvent(
                                    entityId = msg.id,
                                    action = CommandAction.commandAction(msg.action)!!,
                                    data = msg.data
                                )
                            )
                        },
                        1L
                    )
                }
            })
    }
}