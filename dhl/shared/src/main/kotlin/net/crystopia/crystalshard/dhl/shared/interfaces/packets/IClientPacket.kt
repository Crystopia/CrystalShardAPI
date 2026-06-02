package net.crystopia.crystalshard.dhl.shared.interfaces.packets

import net.minecraft.network.protocol.Packet

interface IClientPacket<T> {

    fun createPacket(
        packetObj: T
    ): Packet<*>

}