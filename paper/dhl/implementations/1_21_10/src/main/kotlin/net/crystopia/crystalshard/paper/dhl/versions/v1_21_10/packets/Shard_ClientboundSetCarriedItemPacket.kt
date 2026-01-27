package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetCarriedItemPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetCursorItemPacket
import org.bukkit.craftbukkit.inventory.CraftItemStack

class Shard_ClientboundSetCarriedItemPacket : IPacket<ClientboundSetCarriedItemPacketData> {
    override fun createPacket(packetObj: ClientboundSetCarriedItemPacketData): ClientboundSetCursorItemPacket {
        return ClientboundSetCursorItemPacket(
            CraftItemStack.asNMSCopy(packetObj.item)
        )
    }
}