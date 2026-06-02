package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundDamageEventPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.minecraft.core.Holder
import net.minecraft.world.phys.Vec3
import org.bukkit.craftbukkit.damage.CraftDamageType
import org.bukkit.craftbukkit.entity.CraftEntity
import org.bukkit.entity.Entity

fun ClientPacketFactory.damageEvent(
    entity: Entity,
    damageSource: org.bukkit.damage.DamageSource,
    callback: (packet: ClientPacket<ClientboundDamageEventPacketData>) -> Unit
): ClientPacket<ClientboundDamageEventPacketData> {


    val data = ClientboundDamageEventPacketData(
        (entity as CraftEntity).handle,
        if (damageSource.sourceLocation != null)
            net.minecraft.world.damagesource.DamageSource(
                Holder.direct((damageSource.damageType as CraftDamageType).handle),
                if (damageSource.directEntity != null) (damageSource.directEntity as CraftEntity).handle else null,
                if (damageSource.causingEntity != null) (damageSource.causingEntity as CraftEntity).handle else null,
                Vec3(
                    damageSource.sourceLocation!!.x,
                    damageSource.sourceLocation!!.y,
                    damageSource.sourceLocation!!.z
                )
            )
        else net.minecraft.world.damagesource.DamageSource(
            Holder.direct((damageSource.damageType as CraftDamageType).handle),
            if (damageSource.directEntity != null) (damageSource.directEntity as CraftEntity).handle else null,
            if (damageSource.causingEntity != null) (damageSource.causingEntity as CraftEntity).handle else null,
        )
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.damageEvent(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.damageEvent(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.damageEvent(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.damageEvent(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundDamageEventPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}