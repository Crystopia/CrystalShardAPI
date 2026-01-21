package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundEntityEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket
import net.minecraft.world.entity.Display
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftWorld

class Shard_ClientboundEntityEventPacket : IPacket<ClientboundEntityEventPacketData> {
    override fun createPacket(packetObj: ClientboundEntityEventPacketData): ClientboundEntityEventPacket {
        val fakeEntity = Display.ItemDisplay(EntityType.ITEM_DISPLAY.type, (packetObj.world as CraftWorld).handle)
        return ClientboundEntityEventPacket(
            fakeEntity,
            packetObj.status
        )
    }
}