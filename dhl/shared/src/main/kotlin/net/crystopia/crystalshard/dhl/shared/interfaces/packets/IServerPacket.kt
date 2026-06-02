package net.crystopia.crystalshard.dhl.shared.interfaces.packets

import net.crystopia.crystalshard.dhl.shared.Shard_ServerPacket

interface IServerPacket<T : Any> {
    fun onEvent(data: Shard_ServerPacket, callback: T.() -> Unit)
}
