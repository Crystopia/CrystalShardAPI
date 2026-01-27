package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundContainerSetContentPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.NonNullList
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket
import net.minecraft.world.item.ItemStack
import org.bukkit.craftbukkit.inventory.CraftItemStack

class Shard_ClientboundContainerSetContentPacket : IPacket<ClientboundContainerSetContentPacketData> {

    override fun createPacket(
        packetObj: ClientboundContainerSetContentPacketData
    ): ClientboundContainerSetContentPacket {
        val notnullList = NonNullList.createWithCapacity<ItemStack>(packetObj.items.size)
        packetObj.items.forEach { itemStack -> notnullList.add(CraftItemStack.asNMSCopy(itemStack)) }

        return ClientboundContainerSetContentPacket(
            packetObj.id,
            packetObj.stateId,
            notnullList,
            CraftItemStack.asNMSCopy(packetObj.carriedItem)
        )
    }
}