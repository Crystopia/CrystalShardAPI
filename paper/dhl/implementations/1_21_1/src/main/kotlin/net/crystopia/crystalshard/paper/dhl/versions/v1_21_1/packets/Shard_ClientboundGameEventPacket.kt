package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundGameEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundGameEventPacket
import net.minecraft.world.phys.Vec3

class Shard_ClientboundGameEventPacket : IPacket<ClientboundGameEventPacketData> {

    override fun createPacket(
        packetObj: ClientboundGameEventPacketData
    ): ClientboundGameEventPacket {
        return ClientboundGameEventPacket(
            packetObj.type.id,
            packetObj.action
        )
    }
}