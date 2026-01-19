package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import me.lucko.spark.paper.lib.protobuf.ExperimentalApi
import net.kyori.adventure.text.Component
import net.minecraft.core.component.DataComponentPatch
import net.minecraft.core.component.TypedDataComponent
import net.minecraft.network.HashedStack
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket
import net.minecraft.world.item.ItemStack
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundContainerClickPacket for user-defined clicks in a GUI by the player.
 */
object ServerboundContainerClickPacketUtil {

    data class ContainerClickEvent(
        var containerId: Int,
        var stateId: Int,
        var slotNum: Short,
        var buttonNum: Byte,
        var clickType: ClickType,
        var changedSlots: MutableList<org.bukkit.inventory.ItemStack>,
        var carriedItem: org.bukkit.inventory.ItemStack?,
        var selectedItem : org.bukkit.inventory.ItemStack?
    )

    enum class ClickType(open var id: Int) {
        PICKUP(0), QUICK_MOVE(1), SWAP(2), CLONE(3), THROW(4), QUICK_CRAFT(5), PICKUP_ALL(6),

        // OWN
        SET_DOWN(7);

        companion object {
            private val map = ClickType.entries
            fun getType(id: Int): ClickType {
                return map[id]
            }
        }
    }

    /**
     * Attach the Event to the Player.
     */
    fun attach(
        name: String,
        plugin: JavaPlugin,
        player: Player,
        items: MutableMap<Int, org.bukkit.inventory.ItemStack>,
        callback: ContainerClickEvent.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }
        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundContainerClickPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundContainerClickPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    plugin.server.scheduler.runTaskLater(
                        plugin, Runnable {
                            var clickType = msg.clickType.id()

                            var carried: org.bukkit.inventory.ItemStack? = null
                            if (msg.carriedItem is HashedStack.ActualItem) {
                                if (!items.isEmpty()) {
                                    items.forEach { (i, stack) ->
                                        if (i == msg.slotNum.toInt()) {
                                            carried = stack
                                        }
                                    }
                                }
                            } else {
                                if (msg.clickType.id() == 0) {
                                    clickType = 7
                                }
                            }

                            val changed: MutableList<org.bukkit.inventory.ItemStack> = mutableListOf()
                            if (!msg.changedSlots.isEmpty()) {
                                msg.changedSlots.forEach { (i, stack) ->
                                    if (stack is HashedStack.ActualItem) {
                                        items.forEach { (slot, stack) ->
                                            if (i == slot) {
                                                changed.add(stack)
                                            }
                                        }
                                    }
                                }
                            }

                            var selectedItem : org.bukkit.inventory.ItemStack? = null
                            if (!items.isEmpty()) {
                            items.forEach { (i, stack) ->
                                if (i == msg.slotNum.toInt()) {
                                    selectedItem = stack
                                }
                            }}
                            else selectedItem = null

                            val event = ContainerClickEvent(
                                containerId = msg.containerId,
                                stateId = msg.stateId,
                                slotNum = msg.slotNum,
                                buttonNum = msg.buttonNum,
                                clickType = ClickType.getType(clickType),
                                carriedItem = carried,
                                changedSlots = changed,
                                selectedItem = selectedItem
                            )
                            callback(event)
                        }, 1L
                    )
                }
            })

        return true
    }
}