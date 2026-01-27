package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import com.mojang.datafixers.util.Pair
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetEquipmentPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.MenuType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack
import org.bukkit.craftbukkit.inventory.CraftItemStack

class Shard_ClientboundSetEquipmentPacket : IPacket<ClientboundSetEquipmentPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetEquipmentPacketData
    ): ClientboundSetEquipmentPacket {
        val list = mutableListOf<Pair<EquipmentSlot, ItemStack>>()
        packetObj.equipmentList.forEach { (slot, stack) ->
            list.add(
                Pair(
                    _root_ide_package_.net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.gui.EquipmentSlot.convert(slot).type,
                    CraftItemStack.asNMSCopy(stack)
                )
            )
        }

        return ClientboundSetEquipmentPacket(
            packetObj.entityId, list
        )
    }

}