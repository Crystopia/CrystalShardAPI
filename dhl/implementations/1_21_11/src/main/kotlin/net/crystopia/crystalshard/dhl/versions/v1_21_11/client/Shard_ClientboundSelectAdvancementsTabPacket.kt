package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSelectAdvancementsTabPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundSelectAdvancementsTabPacket
import net.minecraft.resources.Identifier

class Shard_ClientboundSelectAdvancementsTabPacket : IClientPacket<ClientboundSelectAdvancementsTabPacketData> {

    override fun createPacket(
        packetObj: ClientboundSelectAdvancementsTabPacketData
    ): ClientboundSelectAdvancementsTabPacket {
        return ClientboundSelectAdvancementsTabPacket(
            Identifier.tryBuild(packetObj.tab.namespace, packetObj.tab.key)
        )
    }
}