package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundLevelParticlesPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket

class Shard_ClientboundLevelParticlesPacket : IPacket<ClientboundLevelParticlesPacketData> {

    override fun createPacket(
        packetObj: ClientboundLevelParticlesPacketData
    ): ClientboundLevelParticlesPacket {
        val particleData = if (packetObj.particle.options == null) packetObj.particle.particle else packetObj.particle.options

        return ClientboundLevelParticlesPacket(
            particleData as ParticleOptions,
            packetObj.particle.overrideLimiter,
            packetObj.particle.x,
            packetObj.particle.y,
            packetObj.particle.z,
            packetObj.particle.xOffSet,
            packetObj.particle.yOffSet,
            packetObj.particle.zOffSet,
            packetObj.particle.maxSpeed,
            packetObj.particle.count,
        )
    }
}