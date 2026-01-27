package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.InteractEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ClickActionType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Shard_ServerboundInteractPacket : IServerPacket<InteractEvent> {

    override fun attach(
        data: Shard_ServerPacketData,
        callback: InteractEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", "${data.name.namespace}_${data.name.key}", object : MessageToMessageDecoder<ServerboundInteractPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundInteractPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin,
                        Runnable {
                            callback(
                                InteractEvent(
                                    entityId = msg.entityId,
                                    isAttack = msg.isAttack,
                                    sneakKeyPressed = msg.isUsingSecondaryAction,
                                    clickActionType = ClickActionType.clickType(msg.isAttack, msg.isUsingSecondaryAction)
                                )
                            )
                        },
                        1L
                    )
                }
            })
    }
}