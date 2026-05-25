package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMoveEntityPacketData
import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityMoveMode
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
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
                    packetObj.yRot,
                    packetObj.xRot,
                    packetObj.onGround,
                )
            }

            EntityMoveMode.POS_ROT -> {
                return ClientboundMoveEntityPacket.PosRot(
                    packetObj.entityId,
                    packetObj.xa,
                    packetObj.ya,
                    packetObj.za,
                    packetObj.yRot,
                    packetObj.xRot,
                    packetObj.onGround
                )
            }
        }
    }
}