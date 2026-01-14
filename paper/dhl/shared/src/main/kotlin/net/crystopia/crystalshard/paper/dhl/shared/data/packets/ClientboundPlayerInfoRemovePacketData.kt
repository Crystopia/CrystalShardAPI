package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import java.util.*

data class ClientboundPlayerInfoRemovePacketData(
    val uuids: MutableList<UUID>
)
