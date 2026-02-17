package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.ContainerClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ClickType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack


class Shard_ServerboundContainerClickPacket(var items: MutableMap<Int, ItemStack>) :
    IServerPacket<ContainerClickEvent> {
    override fun attach(
        data: Shard_ServerPacketData, callback: ContainerClickEvent.() -> Unit
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
                    if (data.shouldPublish)
                        out.add(msg)

                    Bukkit.getServer().scheduler.runTaskLater(
                        data.plugin, Runnable {
                            var clickType = msg.clickType.ordinal

                            var carried: ItemStack? = null
                            if (msg.carriedItem is net.minecraft.world.item.ItemStack) {
                                carried = CraftItemStack.asBukkitCopy(msg.carriedItem)

                            } else {
                                if (msg.clickType.ordinal == 0) {
                                    clickType = 7
                                }
                            }

                            val changed: MutableMap<Int, ItemStack> = mutableMapOf()
                            if (!msg.changedSlots.isEmpty()) {
                                msg.changedSlots.forEach { (i, stack) ->
                                    changed[i] = CraftItemStack.asBukkitCopy(stack)
                                }
                            }

                            var selectedItem: ItemStack? = null
                            if (!items.isEmpty()) {
                                items.forEach { (i, stack) ->
                                    if (i == msg.slotNum) {
                                        selectedItem = stack
                                    }
                                }
                            } else selectedItem = null

                            val event = ContainerClickEvent(
                                containerId = msg.containerId,
                                stateId = msg.stateId,
                                slotNum = msg.slotNum.toShort(),
                                buttonNum = msg.buttonNum.toByte(),
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