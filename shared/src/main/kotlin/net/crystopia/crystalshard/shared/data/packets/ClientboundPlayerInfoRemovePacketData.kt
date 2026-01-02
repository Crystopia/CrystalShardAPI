package net.crystopia.crystalshard.shared.data.packets

import java.util.*

data class ClientboundPlayerInfoRemovePacketData(
    val uuids: MutableList<UUID>
)
