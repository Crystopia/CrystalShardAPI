package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundLevelParticlesPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.*
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.particles.build
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.particles.ParticleType
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket

class Shard_ClientboundLevelParticlesPacket : IPacket<ClientboundLevelParticlesPacketData> {

    override fun createPacket(
        packetObj: ClientboundLevelParticlesPacketData
    ): ClientboundLevelParticlesPacket {


        val particle = when (packetObj.particle.options) {
            is BlockParticleOption -> (packetObj.particle.options as BlockParticleOption).build()
            is ColorParticleOption -> (packetObj.particle.options as ColorParticleOption).build()
            is DustColorTransitionOptions -> (packetObj.particle.options as DustColorTransitionOptions).build()
            is ItemParticleOption -> (packetObj.particle.options as ItemParticleOption).build()
            is DustParticleOptions -> (packetObj.particle.options as DustParticleOptions).build()
            is SculkChargeParticleOptions -> (packetObj.particle.options as SculkChargeParticleOptions).build()
            is ShriekParticleOption -> (packetObj.particle.options as ShriekParticleOption).build()
            is VibrationParticleOption -> (packetObj.particle.options as VibrationParticleOption).build()
            else -> {}
        }

        return ClientboundLevelParticlesPacket(
            (if (packetObj.particle.options == null) ParticleType.convert(packetObj.particle.particle).id
            else particle as ParticleOptions) as ParticleOptions,
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