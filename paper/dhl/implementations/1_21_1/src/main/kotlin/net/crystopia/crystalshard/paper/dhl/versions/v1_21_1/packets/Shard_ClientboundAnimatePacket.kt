package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundAnimatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundAnimatePacket
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Display
import net.minecraft.world.level.Level
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftWorld

class Shard_ClientboundAnimatePacket : IPacket<ClientboundAnimatePacketData> {
    override fun createPacket(packetObj: ClientboundAnimatePacketData): ClientboundAnimatePacket {
        val fakeEntity = Display.ItemDisplay(EntityType.ITEM_DISPLAY.type, (Bukkit.getWorlds()[0] as CraftWorld).handle)
        return ClientboundAnimatePacket(
            fakeEntity,
            packetObj.animationId
        )
    }
}