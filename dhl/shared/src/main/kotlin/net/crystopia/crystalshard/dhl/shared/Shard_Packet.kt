package net.crystopia.crystalshard.dhl.shared

import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.Packet

class Shard_Packet<DATA : Any> {

    lateinit var packetData: DATA
    lateinit var packetObject: Packet<*>

    fun update(data: DATA): Shard_Packet<DATA> {
        packetData = data
        return this
    }

    fun <T : IPacket<DATA>> build(packetClass: T): Shard_Packet<DATA> {
        packetObject = packetClass.createPacket(packetData)
        return this
    }

}