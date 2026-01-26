package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import java.util.*

data class ClientboundPlayerInfoRemovePacketData(
    val uuids: MutableList<UUID>
)
