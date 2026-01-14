package net.crystopia.crystalshard.paper.simulacrum.displays

import net.crystopia.crystalshard.paper.simulacrum.displays.data.CustomItemDisplayData
import net.crystopia.crystalshard.paper.simulacrum.types.interfaces.displays.IDisplay
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack

class PItemDisplay(
    override var id: NamespacedKey,
    override var type: EntityType<*>,
    override var entity: Display,
) : IDisplay, DisplayInteraction(entity) {
    var data = CustomItemDisplayData()
    lateinit var item: ItemStack
}