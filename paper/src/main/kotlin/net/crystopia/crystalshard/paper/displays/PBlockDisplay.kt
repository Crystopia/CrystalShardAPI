package net.crystopia.crystalshard.extras.displays

import net.crystopia.crystalshard.extras.displays.data.CustomBlockDisplayData
import net.crystopia.crystalshard.shared.interfaces.displays.IDisplay
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.state.BlockState
import org.bukkit.NamespacedKey

class PBlockDisplay(
    override var id: NamespacedKey,
    override var type: EntityType<*>,
    override var entity: Display,
) : IDisplay, DisplayInteraction(entity) {
    var data = CustomBlockDisplayData()
    lateinit var block: BlockState
}