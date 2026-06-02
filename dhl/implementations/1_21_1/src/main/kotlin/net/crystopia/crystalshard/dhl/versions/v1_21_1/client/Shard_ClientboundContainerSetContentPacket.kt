package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundContainerSetContentPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.core.NonNullList
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

class Shard_ClientboundContainerSetContentPacket : IClientPacket<ClientboundContainerSetContentPacketData> {

    override fun createPacket(
        packetObj: ClientboundContainerSetContentPacketData
    ): ClientboundContainerSetContentPacket {
        val notnullList = NonNullList.createWithCapacity<ItemStack>(packetObj.items.size)
        packetObj.items.forEach { itemStack -> notnullList.add(itemStack.value) }

        return ClientboundContainerSetContentPacket(
            packetObj.id,
            packetObj.stateId,
            notnullList,
            packetObj.carriedItem ?: ItemStack(Items.AIR)
        )
    }
}