package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundContainerSetContentPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.NonNullList
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket
import org.bukkit.Material
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

class Shard_ClientboundContainerSetContentPacket : IPacket<ClientboundContainerSetContentPacketData> {

    override fun createPacket(
        packetObj: ClientboundContainerSetContentPacketData
    ): ClientboundContainerSetContentPacket {

        val notnullList = NonNullList.createWithCapacity<net.minecraft.world.item.ItemStack>(packetObj.items.size)
        packetObj.items.forEach { (i, stack) -> notnullList.add(i, CraftItemStack.asNMSCopy(stack)) }

        return ClientboundContainerSetContentPacket(
            packetObj.id,
            packetObj.stateId,
            notnullList,
            CraftItemStack.asNMSCopy(packetObj.carriedItem
                ?: ItemStack(Material.AIR)
            )
        )
    }
}