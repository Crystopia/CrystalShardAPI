package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.enums.gui.MenuType
import net.minecraft.network.chat.Component

data class ClientboundOpenScreenPacketData(
    var id : Int,
    var type: MenuType,
    var title : Component
)
