package net.crystopia.crystalshard.paper.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundContainerSetContentPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket
import org.bukkit.craftbukkit.inventory.CraftItemStack

class Shard_ClientboundContainerSetContentPacket : IPacket<ClientboundContainerSetContentPacketData> {

    override fun createPacket(
        packetObj: ClientboundContainerSetContentPacketData
    ): ClientboundContainerSetContentPacket {
        return ClientboundContainerSetContentPacket(
            packetObj.id,
            packetObj.stateId,
            CraftItemStack.asNMSCopy(packetObj.items),
            CraftItemStack.asNMSCopy(packetObj.carriedItem)
        )
    }
}