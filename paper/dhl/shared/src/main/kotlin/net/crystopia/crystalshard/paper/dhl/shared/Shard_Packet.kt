package net.crystopia.crystalshard.paper.dhl.shared

import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.Packet
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

class Shard_Packet<DATA : Any> {

    lateinit var packetData: DATA
    lateinit var packetObject: Packet<*>

    fun send(players: MutableList<Player>): Shard_Packet<DATA> {
        players.forEach { player ->
            val serverPlayer = (player as CraftPlayer).handle


            val world = Bukkit.getWorld("world") as CraftWorld

            world.handle.dimensionType()


            val connection = serverPlayer.connection
            connection.send(packetObject)
        }
        return this
    }

    fun update(data: DATA): Shard_Packet<DATA> {
        packetData = data
        return this
    }

    fun <T : IPacket<DATA>> build(packetClass: T): Shard_Packet<DATA> {
        packetObject = packetClass.createPacket(packetData)
        return this
    }

}