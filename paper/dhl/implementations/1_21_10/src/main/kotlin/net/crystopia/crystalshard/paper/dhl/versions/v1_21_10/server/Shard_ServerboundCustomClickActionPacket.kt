package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.converter.serialize
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.CustomClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Payload
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.PayloadType
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.kyori.adventure.key.Key
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundCustomClickActionPacket for user-defined clicks by the player.
 */
class Shard_ServerboundCustomClickActionPacket : IServerPacket<CustomClickEvent>  {
    override fun attach(
        data: Shard_ServerPacketData,
        callback: CustomClickEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[data.name.toString()] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder", data.name.toString(), object : MessageToMessageDecoder<ServerboundCustomClickActionPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundCustomClickActionPacket, out: MutableList<Any>
                ) {
                    if (data.shouldPublish)
                        out.add(msg)

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin, Runnable {

                            var data: MutableMap<String, Any> = mutableMapOf()
                            CompoundTag().serialize(msg.payload.get(), data)

                            callback(
                                CustomClickEvent(
                                    key = Key.key(msg.id.namespace, msg.id.path), payload = Payload(
                                        id = msg.payload.get().id, type = PayloadType(
                                            prettyName = msg.payload.get().type.prettyName,
                                            name = msg.payload.get().type.name,
                                            data = data
                                        )
                                    )
                                )
                            )
                        }, 1L
                    )
                }
            })
    }

}