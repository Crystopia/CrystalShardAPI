package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundMoveVehiclePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundMoveVehiclePacket
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftWorld

class Shard_ClientboundMoveVehiclePacket : IPacket<ClientboundMoveVehiclePacketData> {

    override fun createPacket(
        packetObj: ClientboundMoveVehiclePacketData
    ): ClientboundMoveVehiclePacket {
        val fakeEntity = Display.ItemDisplay(
            EntityType.ITEM_DISPLAY,
            (Bukkit.getServer().worlds.first() as CraftWorld).handle
        )
        fakeEntity.setPos(
            packetObj.position.vec3i.x,
            packetObj.position.vec3i.y,
            packetObj.position.vec3i.z
        )
        fakeEntity.xRot = packetObj.yRot
        fakeEntity.yRot = packetObj.yRot

        return ClientboundMoveVehiclePacket(
            fakeEntity
        )
    }
}