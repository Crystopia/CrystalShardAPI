package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.minecraft.network.protocol.Packet
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftServer
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

fun ClientPacketFactory.sendPacket(packet: Packet<*>, players: MutableList<Player>) {
    players.forEach { player ->
        val serverPlayer = (player as CraftPlayer).handle
        serverPlayer.connection.send(packet)
    }
}