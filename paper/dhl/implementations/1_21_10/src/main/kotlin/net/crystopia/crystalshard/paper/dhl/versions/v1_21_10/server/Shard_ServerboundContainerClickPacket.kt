package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.ContainerClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ClickType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.HashedStack
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack


class Shard_ServerboundContainerClickPacket(var items: MutableMap<Int, ItemStack>) :
    IServerPacket<ContainerClickEvent> {
    override fun attach(
        data: Shard_ServerPacketData,
        callback: ContainerClickEvent.() -> Unit
    ) {
        val serverPlayer = (data.player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["${data.name.namespace}_${data.name.key}"] != null) {
            return
        }

        channel.pipeline().addAfter(
            "decoder",
            "${data.name.namespace}_${data.name.key}",
            object : MessageToMessageDecoder<ServerboundContainerClickPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundContainerClickPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin, Runnable {
                            var clickType = msg.clickType.id()

                            var carried: ItemStack? = null
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

                            val changed: MutableMap<Int, ItemStack> = mutableMapOf()
                            if (!msg.changedSlots.isEmpty()) {
                                msg.changedSlots.forEach { (i, stack) ->
                                    if (stack is HashedStack.ActualItem) {
                                        items.forEach { (slot, stack) ->
                                            if (i == slot) {
                                                changed[i] = stack
                                            }
                                        }
                                    }
                                }
                            }

                            var selectedItem: ItemStack? = null
                            if (!items.isEmpty()) {
                                items.forEach { (i, stack) ->
                                    if (i == msg.slotNum.toInt()) {
                                        selectedItem = stack
                                    }
                                }
                            }

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
    }
}