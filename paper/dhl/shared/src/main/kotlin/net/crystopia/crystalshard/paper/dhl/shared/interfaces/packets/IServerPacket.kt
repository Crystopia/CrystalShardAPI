package net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData

interface IServerPacket<T : Any> {
    fun attach(data: Shard_ServerPacketData,callback: T.() -> Unit)
}
