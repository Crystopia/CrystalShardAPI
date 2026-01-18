package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general


import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacketBuilder
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.*
import net.crystopia.crystalshard.paper.dhl.shared.data.packetsid.ClientboundSetPassengersPacketData
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets.*
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.*

object PacketBuilder : IPacketBuilder {

    override fun sendWaypointPacket(data: ClientboundTrackedWaypointPacketData): Packet<*> {
        return Shard_ClientboundTrackedWaypointPacket().createPacket(data)
    }

    override fun setContainerData(data: ClientboundContainerSetDataPacketData): Packet<*> {
        return Shard_ClientboundContainerSetDataPacket().createPacket(data)
    }

    override fun setContainerContent(data: ClientboundContainerSetContentPacketData): Packet<*> {
        return Shard_ClientboundContainerSetContentPacket().createPacket(data)
    }

    override fun setContainerSlot(data: ClientboundContainerSetSlotPacketData): Packet<*> {
        return Shard_ClientboundContainerSetSlotPacket().createPacket(data)
    }

    override fun closeContainerPacket(data: ClientboundContainerClosePacketData): Packet<*> {
        return Shard_ClientboundContainerClosePacket().createPacket(data)
    }

    override fun openScreenPacket(data: ClientboundOpenScreenPacketData): Packet<*> {
        return Shard_ClientboundOpenScreenPacket().createPacket(data)
    }

    override fun updateAttributesPacket(data: ClientboundUpdateAttributesPacketData): Packet<*> {
        return Shard_ClientboundUpdateAttributesPacket().createPacket(data)
    }

    override fun entityEventPacket(data: ClientboundEntityEventPacketData): Packet<*> {
        return Shard_ClientboundEntityEventPacket().createPacket(data)
    }

    override fun animatePacket(data: ClientboundAnimatePacketData): Packet<*> {
        return Shard_ClientboundAnimatePacket().createPacket(data)
    }

    override fun setBlockDestroyStagePacket(data: ClientboundBlockDestructionPacketData): Packet<*> {
        return Shard_ClientboundBlockDestructionPacket().createPacket(data)
    }

    override fun openSignEditorPacket(data: ClientboundOpenSignEditorPacketData): Packet<*> {
        return Shard_ClientboundOpenSignEditorPacket().createPacket(data)
    }

    override fun blockEntityDataPacket(data: ClientboundBlockEntityDataPacketData): Packet<*> {
        return Shard_ClientboundBlockEntityDataPacket().createPacket(data)
    }

    override fun blockUpdatePacket(data: ClientboundBlockUpdatePacketData): Packet<*> {
        return Shard_ClientboundBlockUpdatePacket().createPacket(data)
    }

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