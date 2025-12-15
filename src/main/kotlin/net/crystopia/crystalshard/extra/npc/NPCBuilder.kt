package net.crystopia.crystalshard.extra.npc

import com.mojang.authlib.GameProfile
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.CrystalShard
import net.minecraft.Optionull
import net.minecraft.network.chat.RemoteChatSession
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import net.minecraft.server.level.ServerPlayer
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import java.util.function.Function

object NPCBuilder {

    fun getEntry(npcPlayer: ServerPlayer, gameProfile: GameProfile): ClientboundPlayerInfoUpdatePacket.Entry {

        return ClientboundPlayerInfoUpdatePacket.Entry(
            npcPlayer.getUUID(),
            gameProfile,
            true,
            0,
            npcPlayer.gameMode.gameModeForPlayer,
            npcPlayer.tabListDisplayName,
            true,
            -1,
            Optionull.map<RemoteChatSession?, RemoteChatSession.Data?>(
                npcPlayer.chatSession, Function { obj: RemoteChatSession? -> obj!!.asData() })
        )
    }

    fun inject(player: Player): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["PacketInjector"] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", "PacketInjector", object : MessageToMessageDecoder<ServerboundInteractPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundInteractPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    println(msg)
                    CrystalShard.plugin.server.scheduler.runTaskLater(
                        CrystalShard.plugin,
                        Runnable {
                            val interactPacket = msg.entityId
                            println(interactPacket)
                        },
                        1L
                    );
                }
            })

        return true
    }

}