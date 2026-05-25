package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetDefaultSpawnPositionPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.BlockPos
import net.minecraft.core.GlobalPos
import net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket
import net.minecraft.world.level.storage.LevelData

class Shard_ClientboundSetDefaultSpawnPositionPacket : IPacket<ClientboundSetDefaultSpawnPositionPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetDefaultSpawnPositionPacketData
    ): ClientboundSetDefaultSpawnPositionPacket {
        return ClientboundSetDefaultSpawnPositionPacket(
            LevelData.RespawnData(
                GlobalPos(
                    (packetObj.world).dimension(),
                    BlockPos(
                        packetObj.pos.x,
                        packetObj.pos.y,
                        packetObj.pos.z
                    )
                ),
                packetObj.yaw,
                packetObj.pitch
            )
        )
    }
}