package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EffectInstance
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundUpdateMobEffectPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder

fun ClientPacketFactory.applyMobEffect(
    entityId: Int,
    effect: EffectInstance,
    blend: Boolean,
    callback: (packet: Shard_Packet<ClientboundUpdateMobEffectPacketData>) -> Unit
): Shard_Packet<ClientboundUpdateMobEffectPacketData> {

    val data = ClientboundUpdateMobEffectPacketData(
        entityId, effect, blend
    )


    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.applyMobEffect(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.applyMobEffect(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.applyMobEffect(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.applyMobEffect(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundUpdateMobEffectPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}