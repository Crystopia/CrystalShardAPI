package net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.client

import net.crystopia.crystalshard.paper.dhl.shared.converter.deserialize
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundBlockEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket

class Shard_ClientboundBlockEntityDataPacket : IPacket<ClientboundBlockEntityDataPacketData> {

    override fun createPacket(packetObj: ClientboundBlockEntityDataPacketData): ClientboundBlockEntityDataPacket {
        return ClientboundBlockEntityDataPacket(
            BlockPos(packetObj.blockPos.x,packetObj.blockPos.y,packetObj.blockPos.z),
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.converter.enums.entities.BlockEntityType.convert(
                packetObj.type
            ).type,
            CompoundTag().deserialize(packetObj.nbt)
        )
    }
}