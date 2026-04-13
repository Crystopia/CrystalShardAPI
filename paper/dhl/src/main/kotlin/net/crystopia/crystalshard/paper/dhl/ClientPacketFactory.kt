package net.crystopia.crystalshard.paper.dhl

import net.minecraft.network.protocol.Packet
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

object ClientPacketFactory {

    fun sendPacket(packet: Packet<*>, players: MutableList<Player>) {
        players.forEach { player ->
            val serverPlayer = (player as CraftPlayer).handle
            serverPlayer.connection.send(packet)
        }
    }
}