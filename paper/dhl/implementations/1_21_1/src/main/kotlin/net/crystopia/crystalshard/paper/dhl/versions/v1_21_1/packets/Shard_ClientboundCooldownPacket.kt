package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundCooldownPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundCooldownPacket
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

class Shard_ClientboundCooldownPacket : IPacket<ClientboundCooldownPacketData> {

    override fun createPacket(
        packetObj: ClientboundCooldownPacketData
    ): ClientboundCooldownPacket {
        val stack = ItemStack(packetObj.item)
        val mcStack = CraftItemStack.asNMSCopy(stack)
        return ClientboundCooldownPacket(
            mcStack.item,
            packetObj.duration
        )
    }
}