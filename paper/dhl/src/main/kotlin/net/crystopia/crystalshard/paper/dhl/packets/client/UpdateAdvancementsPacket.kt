package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundUpdateAdvancementsPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_9.data.packets.advancementProgressToShard
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_9.data.packets.advancementToShard
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import org.bukkit.NamespacedKey
import org.bukkit.advancement.Advancement
import org.bukkit.advancement.AdvancementProgress
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Experimental
fun ClientPacketFactory.updateAdvancements(
    reset: Boolean,
    added: MutableList<Advancement>,
    removed: MutableSet<NamespacedKey>,
    progress: MutableMap<NamespacedKey, AdvancementProgress>,
    showAdvancements: Boolean,
    callback: (packet: Shard_Packet<ClientboundUpdateAdvancementsPacketData>) -> Unit
): Shard_Packet<ClientboundUpdateAdvancementsPacketData> {
    val shardPacket = Shard_Packet<ClientboundUpdateAdvancementsPacketData>()
    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            val data = ClientboundUpdateAdvancementsPacketData(
                reset,
                net.crystopia.crystalshard.paper.dhl.converter.v1_21_11.data.packets.advancementToShard(added),
                removed.map {
                    net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(it.namespace, it.key)
                }.toMutableSet(),
                net.crystopia.crystalshard.paper.dhl.converter.v1_21_11.data.packets.advancementProgressToShard(progress),
                showAdvancements
            )
            shardPacket.packetData = data
            ClientPacketBuilder.updateAdvancements(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            val data = ClientboundUpdateAdvancementsPacketData(
                reset,
                net.crystopia.crystalshard.paper.dhl.converter.v1_21_10.data.packets.advancementToShard(added),
                removed.map {
                    net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(it.namespace, it.key)
                }.toMutableSet(),
                net.crystopia.crystalshard.paper.dhl.converter.v1_21_10.data.packets.advancementProgressToShard(progress),
                showAdvancements
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.ClientPacketBuilder.updateAdvancements(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            val data = ClientboundUpdateAdvancementsPacketData(
                reset,
                advancementToShard(added),
                removed.map {
                    net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(it.namespace, it.key)
                }.toMutableSet(),
                advancementProgressToShard(progress),
                showAdvancements
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.ClientPacketBuilder.updateAdvancements(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            val data = ClientboundUpdateAdvancementsPacketData(
                reset,
                net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.advancementToShard(added),
                removed.map {
                    net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(it.namespace, it.key)
                }.toMutableSet(),
                net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.advancementProgressToShard(progress),
                showAdvancements
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.ClientPacketBuilder.updateAdvancements(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}