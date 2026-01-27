package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets



import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetPlayerTeamPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.packets.build
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket
import net.minecraft.world.phys.Vec3

class Shard_ClientboundSetPlayerTeamPacket : IPacket<ClientboundSetPlayerTeamPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetPlayerTeamPacketData
    ): ClientboundSetPlayerTeamPacket {
        return packetObj.build()
    }
}