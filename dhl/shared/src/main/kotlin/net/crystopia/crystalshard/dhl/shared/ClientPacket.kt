package net.crystopia.crystalshard.dhl.shared

import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.Packet

class ClientPacket<DATA : Any> {

    lateinit var packetData: DATA
    lateinit var packetObject: Packet<*>

    fun update(data: DATA): ClientPacket<DATA> {
        packetData = data
        return this
    }

    fun <T : IClientPacket<DATA>> build(packetClass: T): ClientPacket<DATA> {
        packetObject = packetClass.createPacket(packetData)
        return this
    }

}