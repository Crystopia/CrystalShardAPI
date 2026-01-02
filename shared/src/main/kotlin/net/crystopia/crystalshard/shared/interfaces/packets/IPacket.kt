package net.crystopia.crystalshard.shared.interfaces.packets

import net.minecraft.network.protocol.Packet

interface IPacket<T> {

    fun createPacket(
        packetObj: T
    ): Packet<*>

}