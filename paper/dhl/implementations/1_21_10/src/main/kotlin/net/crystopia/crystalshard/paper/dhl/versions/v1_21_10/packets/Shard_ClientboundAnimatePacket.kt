package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundAnimatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundAnimatePacket
import net.minecraft.world.entity.Entity

class Shard_ClientboundAnimatePacket : IPacket<ClientboundAnimatePacketData> {
    override fun createPacket(packetObj: ClientboundAnimatePacketData): ClientboundAnimatePacket {
        return ClientboundAnimatePacket(
            packetObj.entity,
            packetObj.animationId
        )
    }
}