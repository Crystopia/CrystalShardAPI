package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundAddEntityPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetHealthPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket
import net.minecraft.world.phys.Vec3

class Shard_ClientboundSetHealthPacket : IPacket<ClientboundSetHealthPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetHealthPacketData
    ): ClientboundSetHealthPacket {
       return ClientboundSetHealthPacket(
            packetObj.health, packetObj.food, packetObj.saturation
        )
    }
}