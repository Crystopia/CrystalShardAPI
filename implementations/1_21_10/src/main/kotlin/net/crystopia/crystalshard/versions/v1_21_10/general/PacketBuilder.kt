package net.crystopia.crystalshard.versions.v1_21_10.general


import net.crystopia.crystalshard.shared.data.packets.*
import net.crystopia.crystalshard.shared.interfaces.packets.IPacketBuilder
import net.crystopia.crystalshard.versions.v1_21_10.packets.*
import net.minecraft.network.protocol.game.*

object PacketBuilder : IPacketBuilder {

    override fun equipmentPacket(
        data: ClientboundSetEquipmentPacketData
    ): ClientboundSetEquipmentPacket {
        return Shard_ClientboundSetEquipmentPacket().createPacket(data)
    }

    override fun playerInfoUpdatePacket(
        data: ClientboundPlayerInfoUpdatePacketData
    ): ClientboundPlayerInfoUpdatePacket {
        return Shard_ClientboundPlayerInfoUpdatePacket().createPacket(data)
    }

    override fun teleportEntityPacket(
        data: ClientboundTeleportEntityPacketData
    ): ClientboundTeleportEntityPacket {
        return Shard_ClientboundTeleportEntityPacket().createPacket(data)
    }

    override fun playerInfoRemovePacket(
        data: ClientboundPlayerInfoRemovePacketData
    ): ClientboundPlayerInfoRemovePacket {
        return Shard_ClientboundPlayerInfoRemovePacket().createPacket(data)
    }

    override fun removeEntitiesPacket(
        data: ClientboundRemoveEntitiesPacketData
    ): ClientboundRemoveEntitiesPacket {
        return Shard_ClientboundRemoveEntitiesPacket().createPacket(data)
    }

    override fun addEntitiesPacket(
        data: ClientboundAddEntityPacketData
    ): ClientboundAddEntityPacket {
        return Shard_ClientboundAddEntityPacket().createPacket(data)
    }

    override fun rotateHeadPacket(
        data: ClientboundRotateHeadPacketData
    ): ClientboundRotateHeadPacket {
        return Shard_ClientboundRotateHeadPacket().createPacket(data)
    }

    override fun setEntityDataPacket(
        data: ClientboundSetEntityDataPacketData
    ): ClientboundSetEntityDataPacket {
        return Shard_ClientboundSetEntityDataPacket().createPacket(data)
    }

    override fun setPassengersPacket(
        data: ClientboundSetPassengersPacketData
    ): ClientboundSetPassengersPacket {
        return Shard_ClientboundSetPassengersPacket().createPacket(data)
    }
}