package net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundUpdateAdvancementsPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.advancements.AdvancementProgress
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket
import net.minecraft.resources.Identifier
import org.bukkit.craftbukkit.advancement.CraftAdvancement

class Shard_ClientboundUpdateAdvancementsPacket : IPacket<ClientboundUpdateAdvancementsPacketData> {

    override fun createPacket(
        packetObj: ClientboundUpdateAdvancementsPacketData
    ): ClientboundUpdateAdvancementsPacket {
        return ClientboundUpdateAdvancementsPacket(
            packetObj.reset,
            packetObj.added.map { key ->
                return@map (key as CraftAdvancement).handle
            },
            packetObj.removed.map { key ->
                return@map Identifier.fromNamespaceAndPath(key.namespace, key.key)
            }.toSet(),
            packetObj.progress.map { p ->
                val progress = AdvancementProgress()
                progress.update(
                    AdvancementRequirements(
                        p.value.advancement.requirements.requirements.map { it.requiredCriteria }.toMutableList()
                    )
                )
                return@map Pair(
                    Identifier.fromNamespaceAndPath(p.key.namespace, p.key.key),
                    progress
                )
            }.toMap(),
            packetObj.showAdvancements
        )
    }
}