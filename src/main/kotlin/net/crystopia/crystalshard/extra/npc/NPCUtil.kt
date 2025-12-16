package net.crystopia.crystalshard.extra.npc

import com.mojang.datafixers.util.Pair
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.CrystalShard
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

object NPCUtil {

    fun injectToPLayer(player: Player): Boolean {
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
                    CrystalShard.plugin.server.scheduler.runTaskLater(
                        CrystalShard.plugin,
                        Runnable {
                            val interactPacket = msg.entityId

                        },
                        1L
                    );
                }
            })

        return true
    }

    class Packets {

        fun <T : Player> setEquipmentPacket(
            player: T,
            entityId: Int,
            equipmentList: MutableList<Pair<EquipmentSlot?, ItemStack?>?>
        ) {
            val setEquipmentPacket = ClientboundSetEquipmentPacket(
                entityId, equipmentList
            )
            sendPacket(setEquipmentPacket, player)
        }

        fun sendPacket(packet: Packet<*>, player: Player) {
            val serverPlayer = (player as CraftPlayer).handle
            serverPlayer.connection.send(packet)
        }

    }

}