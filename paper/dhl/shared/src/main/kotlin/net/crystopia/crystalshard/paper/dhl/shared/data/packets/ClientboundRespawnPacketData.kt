package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.enums.player.GameMode
import org.bukkit.World
import javax.xml.stream.Location

data class ClientboundRespawnPacketData(
    var world: World,
    var deathLocation: org.bukkit.Location,
    var gameMode: GameMode,
    var isDebug : Boolean,
    var isFlat: Boolean,
    var portalCooldown: Int,
    var datakept: Byte
)
