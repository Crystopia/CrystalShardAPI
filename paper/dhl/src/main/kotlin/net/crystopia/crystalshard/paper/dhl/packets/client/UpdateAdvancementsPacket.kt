package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.builder.AdvancementBuilder
import net.crystopia.crystalshard.dhl.shared.builder.AdvancementProgressBuilder
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundUpdateAdvancementsPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.PAPER_1_21_1
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_10.PAPER_1_21_10
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_11.PAPER_1_21_11
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_9.PAPER_1_21_9
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
    callback: (packet: ClientPacket<ClientboundUpdateAdvancementsPacketData>) -> Unit
): ClientPacket<ClientboundUpdateAdvancementsPacketData> {
    val shardPacket = ClientPacket<ClientboundUpdateAdvancementsPacketData>()
    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            val data = ClientboundUpdateAdvancementsPacketData(
                reset,
                AdvancementBuilder.PAPER_1_21_11(added),
                removed.map {
                    net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(it.namespace, it.key)
                }.toMutableSet(),
                AdvancementProgressBuilder.PAPER_1_21_11(progress),
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
                AdvancementBuilder.PAPER_1_21_10(added),
                removed.map {
                    net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(it.namespace, it.key)
                }.toMutableSet(),
                AdvancementProgressBuilder.PAPER_1_21_10(progress),
                showAdvancements
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.updateAdvancements(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            val data = ClientboundUpdateAdvancementsPacketData(
                reset,
                AdvancementBuilder.PAPER_1_21_9(added),
                removed.map {
                    net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(it.namespace, it.key)
                }.toMutableSet(),
                AdvancementProgressBuilder.PAPER_1_21_9(progress),
                showAdvancements
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.updateAdvancements(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            val data = ClientboundUpdateAdvancementsPacketData(
                reset,
                AdvancementBuilder.PAPER_1_21_1(added),
                removed.map {
                    net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey(it.namespace, it.key)
                }.toMutableSet(),
                AdvancementProgressBuilder.PAPER_1_21_1(progress),
                showAdvancements
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.updateAdvancements(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}