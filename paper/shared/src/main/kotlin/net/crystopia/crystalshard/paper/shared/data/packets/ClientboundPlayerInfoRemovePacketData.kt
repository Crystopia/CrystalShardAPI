package net.crystopia.crystalshard.paper.shared.data.packets

import java.util.*

data class ClientboundPlayerInfoRemovePacketData(
    val uuids: MutableList<UUID>
)
