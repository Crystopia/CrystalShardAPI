package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRespawnPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.enums.player.GameMode
import net.minecraft.core.BlockPos
import net.minecraft.core.GlobalPos
import net.minecraft.core.Holder
import net.minecraft.network.protocol.game.ClientboundRespawnPacket
import net.minecraft.network.protocol.game.CommonPlayerSpawnInfo
import net.minecraft.server.level.ServerLevel
import java.util.*

class Shard_ClientboundRespawnPacket : IPacket<ClientboundRespawnPacketData> {

    override fun createPacket(
        packetObj: ClientboundRespawnPacketData
    ): ClientboundRespawnPacket {

        val mcWorld = packetObj.world
        val deathWorld = packetObj.deathLocation.world

        return ClientboundRespawnPacket(
            CommonPlayerSpawnInfo(
                Holder.direct(mcWorld.dimensionType()),
                mcWorld.dimension(),
                (mcWorld as ServerLevel).seed,
                GameMode.convert(packetObj.gameMode).id,
                null,
                packetObj.isDebug,
                packetObj.isFlat,
                Optional.of(
                    GlobalPos(
                        deathWorld.dimension(),
                        BlockPos(
                            packetObj.deathLocation.x.toInt(), packetObj.deathLocation.y.toInt(), packetObj.deathLocation.z.toInt()
                        )
                    )
                ),
                packetObj.portalCooldown,
                mcWorld.seaLevel
            ),
            packetObj.datakept
        )
    }
}