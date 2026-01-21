package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundBorderPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundLevelParticlesPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket
import net.minecraft.network.protocol.game.ClientboundSetBorderWarningDistancePacket
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.World

class Shard_ClientboundLevelParticlesPacket : IPacket<ClientboundLevelParticlesPacketData> {

    override fun createPacket(
        packetObj: ClientboundLevelParticlesPacketData
    ): ClientboundLevelParticlesPacket {

        TODO("SOON")
        //  return ClientboundLevelParticlesPacket(
        //
        //        )
    }
}