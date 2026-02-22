package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.dhl.shared.data.gui.Slot
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.ContainerClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.ButtonType
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ClickType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IServerPacket
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack


class Shard_ServerboundContainerClickPacket :
    IServerPacket<ContainerClickEvent> {


    private val items: MutableList<Slot>

    constructor(items: MutableList<Slot>) {
        this.items = items
    }

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

                            val changed: MutableList<Slot> = mutableListOf()
                            if (!msg.changedSlots.isEmpty()) {
                                msg.changedSlots.forEach { (i, stack) ->
                                    changed.add(
                                        Slot(
                                            id = i,
                                            item = CraftItemStack.asBukkitCopy(stack)
                                        )
                                    )
                                }
                            }

                            val event = ContainerClickEvent(
                                containerId = msg.containerId,
                                stateId = msg.stateId,
                                slotNum = msg.slotNum.toShort(),
                                buttonNum = parseButton(msg.clickType, msg.buttonNum.toByte(), msg.slotNum),
                                clickType = ClickType.getType(clickType),
                                carriedItem = carried,
                                changedSlots = changed,
                            )
                            callback(event)
                        }, 1L
                    )
                }
            })
    }

    private fun parseButton(mode: net.minecraft.world.inventory.ClickType, button: Byte, slot: Int): ButtonType {
        when (mode) {
            net.minecraft.world.inventory.ClickType.CLONE -> { // 3
                if (button == 2.toByte()) {
                    return ButtonType.MIDDLE_CLICK
                }
            }

            net.minecraft.world.inventory.ClickType.PICKUP -> { // 0
                when (button) {
                    0.toByte() -> {
                        return ButtonType.LEFT_MOUSE_CLICK
                    }

                    1.toByte() -> {
                        return ButtonType.RIGHT_MOUSE_CLICK
                    }

                    0.toByte() if slot == -999 -> {
                        return ButtonType.LEFT_CLICK_OUTSIDE
                    }

                    1.toByte() if slot == -999 -> {
                        return ButtonType.RIGHT_CLICK_OUTSIDE
                    }
                }
            }

            net.minecraft.world.inventory.ClickType.PICKUP_ALL -> { // 6
                if (button == 0.toByte()) {
                    return ButtonType.DOUBLE_CLICK
                } else if (button == 1.toByte()) {
                    throw NotImplementedError("This option is impossible for vanilla clients...")
                }
            }

            net.minecraft.world.inventory.ClickType.QUICK_CRAFT -> { // 5
                when (button) {
                    0.toByte() if slot == -999 -> {
                        return ButtonType.START_LEFT_DRAG
                    }

                    4.toByte() if slot == -999 -> {
                        return ButtonType.START_RIGHT_DRAG
                    }

                    8.toByte() if slot == -999 -> {
                        return ButtonType.START_MIDDLE_DRAG
                    }

                    1.toByte() -> {
                        return ButtonType.ADD_SLOT_LEFT_DRAG
                    }

                    5.toByte() -> {
                        return ButtonType.ADD_SLOT_RIGHT_DRAG
                    }

                    9.toByte() -> {
                        return ButtonType.ADD_SLOT_MIDDLE_DRAG
                    }

                    2.toByte() if slot == -999 -> {
                        return ButtonType.END_LEFT_DRAG
                    }

                    6.toByte() if slot == -999 -> {
                        return ButtonType.END_RIGHT_DRAG
                    }

                    10.toByte() if slot == -999 -> {
                        return ButtonType.END_MIDDLE_DRAG
                    }
                }
            }

            net.minecraft.world.inventory.ClickType.QUICK_MOVE -> { // 1
                if (button == 0.toByte()) {
                    return ButtonType.SHIFT_LEFT_CLICK
                } else if (button == 1.toByte()) {
                    return ButtonType.SHIFT_RIGHT_CLICK
                }
            }

            net.minecraft.world.inventory.ClickType.SWAP -> { // 2
                when (button) {
                    0.toByte() -> {
                        return ButtonType.NUMBER_KEY_ONE
                    }

                    1.toByte() -> {
                        return ButtonType.NUMBER_KEY_TWO
                    }

                    2.toByte() -> {
                        return ButtonType.NUMBER_KEY_THREE
                    }

                    8.toByte() -> {
                        return ButtonType.NUMBER_KEY_NINE
                    }

                    40.toByte() -> {
                        return ButtonType.SWAP_KEY_F
                    }
                }
            }

            net.minecraft.world.inventory.ClickType.THROW -> { // 4
                if (button == 0.toByte()) {
                    return ButtonType.DROP
                } else if (button == 1.toByte()) {
                    return ButtonType.CONTROL_DROP
                }
            }
        }
        return ButtonType.UNKNOWN
    }

}