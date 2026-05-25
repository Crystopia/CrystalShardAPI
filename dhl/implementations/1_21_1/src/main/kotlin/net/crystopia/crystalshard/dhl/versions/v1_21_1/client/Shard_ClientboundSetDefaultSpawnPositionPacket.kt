package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetDefaultSpawnPositionPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
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