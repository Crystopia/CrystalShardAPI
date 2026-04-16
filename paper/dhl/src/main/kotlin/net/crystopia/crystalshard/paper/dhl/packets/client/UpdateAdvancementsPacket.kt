package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundUpdateAdvancementsPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder
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

    val data = ClientboundUpdateAdvancementsPacketData(
        reset, added, removed, progress, showAdvancements
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.updateAdvancements(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.updateAdvancements(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.updateAdvancements(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.updateAdvancements(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundUpdateAdvancementsPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}