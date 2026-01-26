package net.crystopia.crystalshard.paper.simulacrum.types.interfaces.displays

import org.bukkit.NamespacedKey
import org.bukkit.entity.Display

interface IDisplay<T : Display> {
    var id: NamespacedKey
    var type: org.bukkit.entity.EntityType
    var entity: T
}