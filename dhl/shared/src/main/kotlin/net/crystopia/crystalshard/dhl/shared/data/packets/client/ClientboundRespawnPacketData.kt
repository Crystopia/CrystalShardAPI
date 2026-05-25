package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.custom.Location
import net.crystopia.crystalshard.dhl.shared.enums.player.GameMode
import net.minecraft.world.level.Level

data class ClientboundRespawnPacketData(
    var world: Level,
    var deathLocation: Location,
    var gameMode: GameMode,
    var isDebug : Boolean,
    var isFlat: Boolean,
    var portalCooldown: Int,
    var datakept: Byte
)
