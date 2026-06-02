package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import com.mojang.datafixers.util.Pair
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetEquipmentPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack

class Shard_ClientboundSetEquipmentPacket : IClientPacket<ClientboundSetEquipmentPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetEquipmentPacketData
    ): ClientboundSetEquipmentPacket {
        val list = mutableListOf<Pair<EquipmentSlot, ItemStack>>()
        packetObj.equipmentList.forEach { (slot, stack) ->
            list.add(
                Pair(
                    net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.enums.gui.EquipmentSlot.convert(slot).type,
                    stack
                )
            )
        }

        return ClientboundSetEquipmentPacket(
            packetObj.entityId, list
        )
    }

}