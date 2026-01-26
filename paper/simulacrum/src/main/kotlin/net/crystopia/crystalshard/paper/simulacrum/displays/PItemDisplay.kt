package net.crystopia.crystalshard.paper.simulacrum.displays

import net.crystopia.crystalshard.paper.simulacrum.displays.data.CustomItemDisplayData
import net.crystopia.crystalshard.paper.simulacrum.types.interfaces.displays.IDisplay
import org.bukkit.NamespacedKey
import org.bukkit.entity.ItemDisplay
import org.bukkit.inventory.ItemStack

class PItemDisplay(
    override var id: NamespacedKey,
    override var type: org.bukkit.entity.EntityType,
    override var entity: ItemDisplay,
) : IDisplay<ItemDisplay>, DisplayInteraction<ItemDisplay>(entity) {
    var data = CustomItemDisplayData()
    lateinit var item: ItemStack
}