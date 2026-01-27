package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundLevelParticlesPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.BlockParticleOption
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.ColorParticleOption
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.DustColorTransitionOptions
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.DustParticleOptions
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.ItemParticleOption
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.PowerParticleOption
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.SculkChargeParticleOptions
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.ShriekParticleOption
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.SpellParticleOption
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.TrailParticleOption
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.VibrationParticleOption
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.particles.build
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.particles.ParticleType
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
            is PowerParticleOption -> (packetObj.particle.options as PowerParticleOption).build()
            is SculkChargeParticleOptions -> (packetObj.particle.options as SculkChargeParticleOptions).build()
            is ShriekParticleOption -> (packetObj.particle.options as ShriekParticleOption).build()
            is SpellParticleOption -> (packetObj.particle.options as SpellParticleOption).build()
            is TrailParticleOption -> (packetObj.particle.options as TrailParticleOption).build()
            is VibrationParticleOption -> (packetObj.particle.options as VibrationParticleOption).build()
            else -> {}
        }

        return ClientboundLevelParticlesPacket(
            ((if (packetObj.particle.options == null) ParticleType.convert(packetObj.particle.particle)
            else particle as ParticleOptions) as ParticleOptions),
            packetObj.particle.overrideLimiter,
            packetObj.particle.alwaysShow,
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