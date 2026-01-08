package net.crystopia.crystalshard.paper.core.displays

import net.crystopia.crystalshard.paper.core.displays.data.CustomBlockDisplayData
import net.crystopia.crystalshard.paper.shared.interfaces.displays.IDisplay
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.state.BlockState
import org.bukkit.NamespacedKey

class PBlockDisplay(
    override var id: NamespacedKey,
    override var type: EntityType<*>,
    override var entity: Display,
) : net.crystopia.crystalshard.paper.shared.interfaces.displays.IDisplay, DisplayInteraction(entity) {
    var data = CustomBlockDisplayData()
    lateinit var block: BlockState
}