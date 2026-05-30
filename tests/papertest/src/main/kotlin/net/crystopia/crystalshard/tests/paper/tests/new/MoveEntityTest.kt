package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityMoveMode
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.moveEntity
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
import org.bukkit.command.CommandSender
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class MoveEntityTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            val connection = (player as CraftPlayer).handle.connection
           /*
            connection.connection.channel.pipeline().addAfter(
                "decoder", "sdfsdgdsfg", object : MessageToMessageDecoder<ServerboundMovePlayerPacket.PosRot>() {
                    override fun decode(
                        ctx: ChannelHandlerContext, msg: ServerboundMovePlayerPacket.PosRot, out: MutableList<Any>
                    ) {
                        // out.add(msg)

                        println(msg.x)
                    }
                })
            */

            ClientPacketFactory.moveEntity(
                mode = EntityMoveMode.POS_ROT,
                entity = player.getNearbyEntities(2.0, 2.0, 2.0).filter { it.type == EntityType.PIG }
                    .toMutableList()[0],
                xa = ((player.x * 4096) - (player.x * 4096)).toInt().toShort(),
                ya = ((player.y * 4096) - (player.y * 4096)).toInt().toShort(),
                za = ((player.z * 4096) - (player.z * 4096)).toInt().toShort(),
                yRot = (player.handle.yRot * 256.0 / 360.0).toInt().toByte(),
                xRot = (player.handle.xRot * 256.0 / 360.0).toInt().toByte(),
                onGround = true,
                hasRot = true,
                hasPos = true
            ) { it.send(mutableListOf(player)) }
            println("MoveEntity OK")
        }
    }
}