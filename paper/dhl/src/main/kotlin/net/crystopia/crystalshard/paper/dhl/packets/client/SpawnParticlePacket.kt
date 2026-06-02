package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundLevelParticlesPacketData
import net.crystopia.crystalshard.dhl.shared.data.particles.Particle
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoDhlTypeToConvert
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.types.particles.*
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import org.bukkit.craftbukkit.block.data.CraftBlockData
import org.bukkit.craftbukkit.entity.CraftEntity
import org.bukkit.craftbukkit.inventory.CraftItemStack

fun ClientPacketFactory.spawnParticle(
    particle: net.crystopia.crystalshard.paper.dhl.types.particles.Particle<*, *>,
    callback: (packet: ClientPacket<ClientboundLevelParticlesPacketData>) -> Unit
): ClientPacket<ClientboundLevelParticlesPacketData> {

    val data = ClientboundLevelParticlesPacketData(
        Particle(
            particle = particle.particle,
            options = when (particle.options) {
                is BlockParticleOption -> {
                    net.crystopia.crystalshard.dhl.shared.data.particles.BlockParticleOption(
                        (particle.options as BlockParticleOption).type,
                        ((particle.options as BlockParticleOption).block.createBlockData() as CraftBlockData).state.block
                    )
                }

                is ColorParticleOption -> {
                    net.crystopia.crystalshard.dhl.shared.data.particles.ColorParticleOption(
                        (particle.options as ColorParticleOption).type,
                        (particle.options as ColorParticleOption).color,
                    )
                }

                is DustColorTransitionOptions -> {
                    net.crystopia.crystalshard.dhl.shared.data.particles.DustColorTransitionOptions(
                        (particle.options as DustColorTransitionOptions).fromColor,
                        (particle.options as DustColorTransitionOptions).toColor,
                        (particle.options as DustColorTransitionOptions).scale,
                    )
                }

                is ItemParticleOption -> {
                    net.crystopia.crystalshard.dhl.shared.data.particles.ItemParticleOption(
                        (particle.options as ItemParticleOption).type,
                        CraftItemStack.asNMSCopy((particle.options as ItemParticleOption).item),
                    )
                }

                is DustParticleOptions -> {
                    net.crystopia.crystalshard.dhl.shared.data.particles.DustParticleOptions(
                        (particle.options as DustParticleOptions).color,
                        (particle.options as DustParticleOptions).scale,
                    )
                }

                is PowerParticleOption -> {
                    net.crystopia.crystalshard.dhl.shared.data.particles.PowerParticleOption(
                        (particle.options as PowerParticleOption).type,
                        (particle.options as PowerParticleOption).power,
                    )
                }

                is SculkChargeParticleOptions -> {
                    net.crystopia.crystalshard.dhl.shared.data.particles.SculkChargeParticleOptions(
                        (particle.options as SculkChargeParticleOptions).roll,
                    )
                }

                is ShriekParticleOption -> {
                    net.crystopia.crystalshard.dhl.shared.data.particles.ShriekParticleOption(
                        (particle.options as ShriekParticleOption).delay,
                    )
                }

                is SpellParticleOption -> {
                    net.crystopia.crystalshard.dhl.shared.data.particles.SpellParticleOption(
                        (particle.options as SpellParticleOption).type,
                        (particle.options as SpellParticleOption).color,
                        (particle.options as SpellParticleOption).power
                    )
                }

                is TrailParticleOption -> {
                    net.crystopia.crystalshard.dhl.shared.data.particles.TrailParticleOption(
                        (particle.options as TrailParticleOption).x,
                        (particle.options as TrailParticleOption).y,
                        (particle.options as TrailParticleOption).z,
                        (particle.options as TrailParticleOption).color,
                        (particle.options as TrailParticleOption).duration
                    )
                }

                is VibrationParticleOption -> {
                    net.crystopia.crystalshard.dhl.shared.data.particles.VibrationParticleOption(
                        ((particle.options as VibrationParticleOption).entity as CraftEntity).handle,
                            (particle.options as VibrationParticleOption).offSet,
                            (particle.options as VibrationParticleOption).blockPos,
                            (particle.options as VibrationParticleOption).arrivalInTicks,
                    )
                }

                else -> throw NoDhlTypeToConvert("No type found")

            },
            overrideLimiter = particle.overrideLimiter,
            alwaysShow = particle.alwaysShow,
            x = particle.x,
            y = particle.y,
            z = particle.z,
            xOffSet = particle.xOffSet,
            yOffSet = particle.yOffSet,
            zOffSet = particle.zOffSet,
            maxSpeed = particle.maxSpeed,
            count = particle.count,
        )
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.spawnParticle(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.spawnParticle(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.spawnParticle(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.spawnParticle(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundLevelParticlesPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}