package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityMoveMode
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMoveEntityPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket

class Shard_ClientboundMoveEntityPacket : IPacket<ClientboundMoveEntityPacketData> {

    override fun createPacket(
        packetObj: ClientboundMoveEntityPacketData
    ): ClientboundMoveEntityPacket {

        when (packetObj.mode) {
            EntityMoveMode.POS -> {
                return ClientboundMoveEntityPacket.Pos(
                    packetObj.entityId,
                    packetObj.xa,
                    packetObj.ya,
                    packetObj.za,
                    packetObj.onGround
                )
            }

            EntityMoveMode.ROT -> {
                return ClientboundMoveEntityPacket.Rot(
                    packetObj.entityId,
                    packetObj.xRot,
                    packetObj.yRot,
                    packetObj.onGround,
                )
            }

            EntityMoveMode.POS_ROT -> {
                return ClientboundMoveEntityPacket.PosRot(
                    packetObj.entityId,
                    packetObj.xa,
                    packetObj.ya,
                    packetObj.za,
                    packetObj.xRot,
                    packetObj.yRot,
                    packetObj.onGround
                )
            }
        }
    }
}