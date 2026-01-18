package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.MenuType
import net.kyori.adventure.text.Component

data class ClientboundOpenScreenPacketData(
    var id : Int,
    var type: MenuType,
    var title : Component
)
