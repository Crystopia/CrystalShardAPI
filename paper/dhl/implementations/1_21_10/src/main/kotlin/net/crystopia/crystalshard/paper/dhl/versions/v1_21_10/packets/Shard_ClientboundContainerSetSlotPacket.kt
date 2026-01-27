package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundContainerSetSlotPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket
import org.bukkit.craftbukkit.inventory.CraftItemStack

class Shard_ClientboundContainerSetSlotPacket : IPacket<ClientboundContainerSetSlotPacketData> {

    override fun createPacket(
        packetObj: ClientboundContainerSetSlotPacketData
    ): ClientboundContainerSetSlotPacket {
        return ClientboundContainerSetSlotPacket(
            packetObj.id,
            packetObj.revision,
            packetObj.slot,
            CraftItemStack.asNMSCopy(packetObj.item)
        )
    }
}