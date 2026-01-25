package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetDefaultSpawnPositionPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket

class Shard_ClientboundSetDefaultSpawnPositionPacket : IPacket<ClientboundSetDefaultSpawnPositionPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetDefaultSpawnPositionPacketData
    ): ClientboundSetDefaultSpawnPositionPacket {
        return ClientboundSetDefaultSpawnPositionPacket(
            BlockPos(
                packetObj.pos.x,
                packetObj.pos.y,
                packetObj.pos.z
            ),
            packetObj.angle
        )
    }
}