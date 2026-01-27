package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundEntityEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket
import net.minecraft.world.entity.Display
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.entity.CraftEntityType

class Shard_ClientboundEntityEventPacket : IPacket<ClientboundEntityEventPacketData> {
    override fun createPacket(packetObj: ClientboundEntityEventPacketData): ClientboundEntityEventPacket {
        val fakeEntity = Display.ItemDisplay(CraftEntityType.bukkitToMinecraft(org.bukkit.entity.EntityType.TEXT_DISPLAY), (Bukkit.getWorlds()[0] as CraftWorld).handle)
        fakeEntity.id = packetObj.entityId

        return ClientboundEntityEventPacket(
            fakeEntity,
            packetObj.status
        )
    }
}