package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundDamageEventPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.minecraft.core.Holder
import net.minecraft.world.phys.Vec3
import org.bukkit.craftbukkit.damage.CraftDamageType
import org.bukkit.craftbukkit.entity.CraftEntity
import org.bukkit.entity.Entity

fun ClientPacketFactory.damageEvent(
    entity: Entity,
    damageSource: org.bukkit.damage.DamageSource,
    callback: (packet: Shard_Packet<ClientboundDamageEventPacketData>) -> Unit
): Shard_Packet<ClientboundDamageEventPacketData> {

    val data = ClientboundDamageEventPacketData(
        (entity as CraftEntity).handle,
        if (damageSource.sourceLocation != null)
            net.minecraft.world.damagesource.DamageSource(
                Holder.direct(CraftDamageType.bukkitToMinecraft(damageSource.damageType)),
                (damageSource.directEntity as CraftEntity).handle,
                (damageSource.causingEntity as CraftEntity).handle,
                Vec3(
                    damageSource.sourceLocation!!.x,
                    damageSource.sourceLocation!!.y,
                    damageSource.sourceLocation!!.z
                )
            )
        else net.minecraft.world.damagesource.DamageSource(
            Holder.direct(CraftDamageType.bukkitToMinecraft(damageSource.damageType)),
            (damageSource.directEntity as CraftEntity).handle,
            (damageSource.causingEntity as CraftEntity).handle
        )
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.damageEvent(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.PacketBuilder.damageEvent(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.PacketBuilder.damageEvent(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.damageEvent(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundDamageEventPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}