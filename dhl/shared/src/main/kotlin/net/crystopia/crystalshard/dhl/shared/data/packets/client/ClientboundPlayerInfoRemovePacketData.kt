package net.crystopia.crystalshard.dhl.shared.data.packets.client

import java.util.*

data class ClientboundPlayerInfoRemovePacketData(
    val uuids: MutableList<UUID>
)
