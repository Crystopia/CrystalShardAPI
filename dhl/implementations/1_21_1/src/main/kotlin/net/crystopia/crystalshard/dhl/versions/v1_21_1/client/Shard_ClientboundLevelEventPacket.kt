package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundLevelEventPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket

class Shard_ClientboundLevelEventPacket : IClientPacket<ClientboundLevelEventPacketData> {

    override fun createPacket(
        packetObj: ClientboundLevelEventPacketData
    ): ClientboundLevelEventPacket {
        return ClientboundLevelEventPacket(
            packetObj.type,
            BlockPos(
                packetObj.pos.x,
                packetObj.pos.y,
                packetObj.pos.z
            ),
            packetObj.data,
            packetObj.globalEvent
        )
    }
}