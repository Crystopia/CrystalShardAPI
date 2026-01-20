package net.crystopia.crystalshard.paper.simulacrum.displays

import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityType
import net.crystopia.crystalshard.paper.simulacrum.displays.data.CustomBlockDisplayData
import net.crystopia.crystalshard.paper.simulacrum.types.interfaces.displays.IDisplay
import net.minecraft.world.level.block.state.BlockState
import org.bukkit.NamespacedKey
import org.bukkit.entity.BlockDisplay

class PBlockDisplay(
    override var id: NamespacedKey,
    override var type: EntityType,
    override var entity: BlockDisplay
) : IDisplay<BlockDisplay>, DisplayInteraction<BlockDisplay>(entity) {
    var data = CustomBlockDisplayData()
    lateinit var block: BlockState
}