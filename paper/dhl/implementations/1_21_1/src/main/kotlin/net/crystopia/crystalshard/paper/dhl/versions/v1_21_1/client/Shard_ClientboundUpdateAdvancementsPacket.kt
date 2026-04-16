package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundUpdateAdvancementsPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.advancements.AdvancementProgress
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket
import net.minecraft.resources.ResourceLocation
import org.bukkit.craftbukkit.advancement.CraftAdvancement

class Shard_ClientboundUpdateAdvancementsPacket : IPacket<ClientboundUpdateAdvancementsPacketData> {

    override fun createPacket(
        packetObj: ClientboundUpdateAdvancementsPacketData
    ): ClientboundUpdateAdvancementsPacket {
        return ClientboundUpdateAdvancementsPacket(
            packetObj.reset, packetObj.added.map { key ->
                return@map (key as CraftAdvancement).handle
            }, packetObj.removed.map { key ->
                return@map ResourceLocation.tryBuild(key.namespace, key.key)
            }.toSet(), packetObj.progress.map { p ->
                val progress = AdvancementProgress()
                progress.update(
                    AdvancementRequirements(
                        listOf(p.value.advancement.criteria.map { it }.toList())
                    )
                )
                return@map Pair(
                    ResourceLocation.tryBuild(p.key.namespace, p.key.key), progress
                )
            }.toMap()
        )
    }
}