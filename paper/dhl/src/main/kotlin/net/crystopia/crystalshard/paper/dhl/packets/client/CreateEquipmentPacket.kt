package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetEquipmentPacketData
import net.crystopia.crystalshard.dhl.shared.enums.gui.EquipmentSlot
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

fun ClientPacketFactory.createEquipment(
    entityId: Int,
    equipmentList: MutableList<Pair<EquipmentSlot, ItemStack>>,
    callback: (packet: ClientPacket<ClientboundSetEquipmentPacketData>) -> Unit
): ClientPacket<ClientboundSetEquipmentPacketData> {

    val data = ClientboundSetEquipmentPacketData(
        entityId, equipmentList.map {
            Pair(
                it.first,
                CraftItemStack.asNMSCopy(it.second)
            )
        }.toMutableList()
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.equipmentPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.ClientPacketBuilder.equipmentPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.ClientPacketBuilder.equipmentPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.ClientPacketBuilder.equipmentPacket(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }


    val shardPacket = ClientPacket<ClientboundSetEquipmentPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}