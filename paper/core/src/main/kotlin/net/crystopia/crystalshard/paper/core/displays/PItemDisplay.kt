package net.crystopia.crystalshard.paper.core.displays

import net.crystopia.crystalshard.paper.core.displays.data.CustomItemDisplayData
import net.crystopia.crystalshard.paper.core.packets.ServerboundInteractPacketUtil
import net.crystopia.crystalshard.paper.core.packets.ServerboundInteractPacketUtil.ClickActionType
import net.crystopia.crystalshard.paper.shared.interfaces.displays.IDisplay
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class PItemDisplay(
    override var id: NamespacedKey,
    override var type: EntityType<*>,
    override var entity: Display,
) : net.crystopia.crystalshard.paper.shared.interfaces.displays.IDisplay, DisplayInteraction(entity) {
    var data = CustomItemDisplayData()
    lateinit var item: ItemStack
}