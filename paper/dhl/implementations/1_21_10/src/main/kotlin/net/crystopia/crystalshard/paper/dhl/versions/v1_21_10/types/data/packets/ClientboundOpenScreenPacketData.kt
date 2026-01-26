package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.MenuType
import net.kyori.adventure.text.Component

data class ClientboundOpenScreenPacketData(
    var id : Int,
    var type: MenuType,
    var title : Component
)
