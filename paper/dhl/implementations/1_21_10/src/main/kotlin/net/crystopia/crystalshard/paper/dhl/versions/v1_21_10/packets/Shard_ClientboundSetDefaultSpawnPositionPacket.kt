package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetDefaultSpawnPositionPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.BlockPos
import net.minecraft.core.GlobalPos
import net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket
import net.minecraft.world.level.storage.LevelData
import org.bukkit.craftbukkit.CraftWorld

class Shard_ClientboundSetDefaultSpawnPositionPacket : IPacket<ClientboundSetDefaultSpawnPositionPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetDefaultSpawnPositionPacketData
    ): ClientboundSetDefaultSpawnPositionPacket {
        return ClientboundSetDefaultSpawnPositionPacket(
            LevelData.RespawnData(
                GlobalPos(
                    (packetObj.world as CraftWorld).handle.dimension(),
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