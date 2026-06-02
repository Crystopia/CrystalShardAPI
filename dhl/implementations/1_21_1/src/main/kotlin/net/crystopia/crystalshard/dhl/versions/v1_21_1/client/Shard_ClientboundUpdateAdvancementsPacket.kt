package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundUpdateAdvancementsPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementProgress
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket
import net.minecraft.resources.ResourceLocation

class Shard_ClientboundUpdateAdvancementsPacket : IClientPacket<ClientboundUpdateAdvancementsPacketData> {

    override fun createPacket(
        packetObj: ClientboundUpdateAdvancementsPacketData
    ): ClientboundUpdateAdvancementsPacket {
        return ClientboundUpdateAdvancementsPacket(
            packetObj.reset, packetObj.added.map { data ->
                return@map AdvancementHolder(ResourceLocation.tryBuild(data.key.namespace, data.key.key)!!, data.value)
            }, packetObj.removed.map { key ->
                return@map ResourceLocation.tryBuild(key.namespace, key.key)
            }.toSet(), packetObj.progress.map { p ->
                val progress = AdvancementProgress()
                progress.update(
                    AdvancementRequirements(
                        listOf(p.value.remainingCriteria.map { it }.toList())
                    )
                )
                return@map Pair(
                    ResourceLocation.tryBuild(p.key.namespace, p.key.key), progress
                )
            }.toMap()
        )
    }
}