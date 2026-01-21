package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundLevelParticlesPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket

class Shard_ClientboundLevelParticlesPacket : IPacket<ClientboundLevelParticlesPacketData> {

    override fun createPacket(
        packetObj: ClientboundLevelParticlesPacketData
    ): ClientboundLevelParticlesPacket {

        return ClientboundLevelParticlesPacket(
            (if (packetObj.particle.options == null) packetObj.particle.particle.id
            else packetObj.particle.options!!.build() as ParticleOptions) as ParticleOptions,
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