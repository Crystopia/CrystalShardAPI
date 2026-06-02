package net.crystopia.crystalshard.dhl.shared.interfaces.packets

import net.crystopia.crystalshard.dhl.shared.ServerPacket

interface IServerPacket<T : Any> {
    fun onEvent(data: ServerPacket, callback: T.() -> Unit)
}
