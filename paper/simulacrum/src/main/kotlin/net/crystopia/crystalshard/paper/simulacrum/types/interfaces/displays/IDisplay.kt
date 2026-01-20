package net.crystopia.crystalshard.paper.simulacrum.types.interfaces.displays

import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityType
import org.bukkit.NamespacedKey
import org.bukkit.entity.Display

interface IDisplay<T : Display> {
    var id: NamespacedKey
    var type: EntityType
    var entity: T
}