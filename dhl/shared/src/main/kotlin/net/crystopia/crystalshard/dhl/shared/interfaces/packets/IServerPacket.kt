package net.crystopia.crystalshard.dhl.shared.interfaces.packets

import net.crystopia.crystalshard.dhl.shared.data.packets.server.Shard_ServerPacketData

interface IServerPacket<T : Any> {
    fun attach(data: Shard_ServerPacketData,callback: T.() -> Unit)
}
