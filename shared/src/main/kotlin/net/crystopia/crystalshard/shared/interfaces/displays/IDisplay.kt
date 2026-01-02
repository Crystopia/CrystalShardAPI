package net.crystopia.crystalshard.shared.interfaces.displays

import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import org.bukkit.NamespacedKey

interface IDisplay {

    var id: NamespacedKey
    var type: EntityType<*>
    var entity: Display
}