package net.crystopia.crystalshard.paper.simulacrum.displays

import net.crystopia.crystalshard.paper.simulacrum.displays.data.CustomBlockDisplayData
import net.crystopia.crystalshard.paper.simulacrum.types.interfaces.displays.IDisplay
import net.minecraft.world.level.block.state.BlockState
import org.bukkit.NamespacedKey
import org.bukkit.entity.BlockDisplay

class PBlockDisplay(
    override var id: NamespacedKey,
    override var type: net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityType,
    override var entity: BlockDisplay
) : IDisplay<BlockDisplay>, DisplayInteraction<BlockDisplay>(entity) {
    var data = CustomBlockDisplayData()
    lateinit var block: BlockState
}