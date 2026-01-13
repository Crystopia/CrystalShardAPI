package net.crystopia.crystalshard.paper.shared.data.packets

import net.kyori.adventure.text.Component
import net.minecraft.world.inventory.MenuType

data class ClientboundOpenScreenPacketData(
    var id : Int,
    var type: MenuType<*>,
    var title : Component
)
