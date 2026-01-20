package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundRotateHeadPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket
import net.minecraft.world.entity.Display
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftWorld

class Shard_ClientboundRotateHeadPacket : IPacket<ClientboundRotateHeadPacketData> {

    override fun createPacket(
        packetObj: ClientboundRotateHeadPacketData
    ): ClientboundRotateHeadPacket {
        val fakeEntity = Display.ItemDisplay(EntityType.ITEM_DISPLAY.type, (Bukkit.getWorlds()[0] as CraftWorld).handle)
        val angelMultiplier = 256F / 360F

        return ClientboundRotateHeadPacket(
            fakeEntity, ((packetObj.yaw * angelMultiplier).toInt().toByte())
        )
    }
}