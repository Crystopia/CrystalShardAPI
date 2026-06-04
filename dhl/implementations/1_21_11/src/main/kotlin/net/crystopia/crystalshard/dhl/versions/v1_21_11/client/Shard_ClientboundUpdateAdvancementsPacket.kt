package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundUpdateAdvancementsPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementProgress
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.network.protocol.game.ClientboundSelectAdvancementsTabPacket
import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket
import net.minecraft.resources.Identifier

class Shard_ClientboundUpdateAdvancementsPacket : IClientPacket<ClientboundUpdateAdvancementsPacketData> {

    override fun createPacket(
        packetObj: ClientboundUpdateAdvancementsPacketData
    ): ClientboundUpdateAdvancementsPacket {
        return ClientboundUpdateAdvancementsPacket(
            packetObj.reset, packetObj.added.map { key ->
                return@map AdvancementHolder(
                    Identifier.tryBuild(key.key.namespace, key.key.key)!!, key.value
                )
            },
            packetObj.removed.map { key ->
                return@map Identifier.fromNamespaceAndPath(key.namespace, key.key)
            }.toSet(),
            packetObj.progress.map { p ->
                val progress = AdvancementProgress()
                progress.update(
                    AdvancementRequirements(
                        listOf(p.value.remainingCriteria.map { it }.toMutableList())
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