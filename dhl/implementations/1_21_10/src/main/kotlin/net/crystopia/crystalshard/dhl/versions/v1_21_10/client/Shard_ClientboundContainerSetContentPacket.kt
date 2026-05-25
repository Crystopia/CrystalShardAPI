package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundContainerSetContentPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.NonNullList
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket

class Shard_ClientboundContainerSetContentPacket : IPacket<ClientboundContainerSetContentPacketData> {

    override fun createPacket(
        packetObj: ClientboundContainerSetContentPacketData
    ): ClientboundContainerSetContentPacket {

        val notnullList = NonNullList.createWithCapacity<net.minecraft.world.item.ItemStack>(packetObj.items.size)
        packetObj.items.forEach { (i, stack) -> notnullList.add(i, stack) }

        return ClientboundContainerSetContentPacket(
            packetObj.id,
            packetObj.stateId,
            notnullList,
            packetObj.carriedItem
                ?: net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.AIR)
        )
    }
}