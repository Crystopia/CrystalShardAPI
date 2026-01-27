package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundRespawnPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.player.GameMode
import net.minecraft.core.BlockPos
import net.minecraft.core.GlobalPos
import net.minecraft.core.Holder
import net.minecraft.network.protocol.game.ClientboundRespawnPacket
import net.minecraft.network.protocol.game.CommonPlayerSpawnInfo
import org.bukkit.craftbukkit.CraftWorld
import java.util.*

class Shard_ClientboundRespawnPacket : IPacket<ClientboundRespawnPacketData> {

    override fun createPacket(
        packetObj: ClientboundRespawnPacketData
    ): ClientboundRespawnPacket {

        val mcWorld = packetObj.world as CraftWorld
        val deathWorld = packetObj.deathLocation.world as CraftWorld

        return ClientboundRespawnPacket(
            CommonPlayerSpawnInfo(
                Holder.direct(mcWorld.handle.dimensionType()),
                mcWorld.handle.dimension(),
                mcWorld.seed,
                GameMode.convert(packetObj.gameMode).id,
                null,
                packetObj.isDebug,
                packetObj.isFlat,
                Optional.of(
                    GlobalPos(
                        deathWorld.handle.dimension(),
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