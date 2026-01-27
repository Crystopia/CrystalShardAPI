package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundLevelEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket

class Shard_ClientboundLevelEventPacket : IPacket<ClientboundLevelEventPacketData> {

    override fun createPacket(
        packetObj: ClientboundLevelEventPacketData
    ): ClientboundLevelEventPacket {
        return ClientboundLevelEventPacket(
            packetObj.type,
            BlockPos(
                packetObj.pos.x,
                packetObj.pos.y,
                packetObj.pos.z
            ),
            packetObj.data,
            packetObj.globalEvent
        )
    }
}