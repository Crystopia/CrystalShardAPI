package net.crystopia.crystalshard.paper.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundContainerSetSlotPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
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