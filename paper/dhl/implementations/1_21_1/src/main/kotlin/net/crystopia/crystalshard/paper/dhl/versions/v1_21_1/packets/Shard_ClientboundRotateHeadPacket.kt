package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundRotateHeadPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket
import net.minecraft.world.entity.Display
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.entity.CraftEntityType

class Shard_ClientboundRotateHeadPacket : IPacket<ClientboundRotateHeadPacketData> {

    override fun createPacket(
        packetObj: ClientboundRotateHeadPacketData
    ): ClientboundRotateHeadPacket {
        val fakeEntity = Display.ItemDisplay(CraftEntityType.bukkitToMinecraft(org.bukkit.entity.EntityType.TEXT_DISPLAY), (Bukkit.getWorlds()[0] as CraftWorld).handle)
        fakeEntity.id = packetObj.entity
        val angleMultiplier = 256F / 360F

        return ClientboundRotateHeadPacket(
            fakeEntity, ((packetObj.yaw * angleMultiplier).toInt().toByte())
        )
    }
}