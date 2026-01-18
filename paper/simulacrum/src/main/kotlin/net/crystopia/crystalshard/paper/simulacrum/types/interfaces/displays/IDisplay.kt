package net.crystopia.crystalshard.paper.simulacrum.types.interfaces.displays

import org.bukkit.NamespacedKey
import org.bukkit.entity.Display

interface IDisplay<T : Display> {
    var id: NamespacedKey
    var type: net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityType
    var entity: T
}