package net.crystopia.crystalshard.extras.displays

import net.crystopia.crystalshard.extras.displays.data.CustomBlockDisplayData
import net.crystopia.crystalshard.extras.packets.ServerboundInteractPacketUtil
import net.crystopia.crystalshard.extras.packets.ServerboundInteractPacketUtil.ClickActionType
import net.crystopia.crystalshard.shared.interfaces.displays.IDisplay
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.state.BlockState
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class PBlockDisplay(
    override var id: NamespacedKey,
    override var type: EntityType<*>,
    override var entity: Display,
) : IDisplay, DisplayInteraction(entity) {
    var data = CustomBlockDisplayData()
    lateinit var block: BlockState

    fun onInteract(
        key: NamespacedKey,
        plugin: JavaPlugin,
        player: Player,
        callback: (clickType: ClickActionType, msg: ServerboundInteractPacket) -> Unit
    ) {
        ServerboundInteractPacketUtil.attach("${key.namespace}:${key.key}", plugin, player) { clickType, msg ->
            callback(clickType, msg)
        }
    }
}