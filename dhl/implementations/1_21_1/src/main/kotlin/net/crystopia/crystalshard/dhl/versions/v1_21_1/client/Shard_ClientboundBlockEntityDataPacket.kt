package net.crystopia.crystalshard.dhl.versions.v1_21_1.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBlockEntityDataPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.data.nbt.deserialize
import net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.enums.entities.BlockEntityType
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket

class Shard_ClientboundBlockEntityDataPacket : IClientPacket<ClientboundBlockEntityDataPacketData> {

    override fun createPacket(packetObj: net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBlockEntityDataPacketData): ClientboundBlockEntityDataPacket {
        return ClientboundBlockEntityDataPacket(
            BlockPos(packetObj.blockPos.x, packetObj.blockPos.y, packetObj.blockPos.z),
            BlockEntityType.convert(packetObj.type).type,
            CompoundTag().deserialize(packetObj.nbt)
        )
    }

}