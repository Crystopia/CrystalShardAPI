package net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.*
import net.minecraft.network.protocol.Packet

interface IPacketBuilder {

    fun setContainerSlot(data: ClientboundContainerSetSlotPacketData): Packet<*>
    fun closeContainerPacket(data: ClientboundContainerClosePacketData): Packet<*>
    fun openScreenPacket(data: ClientboundOpenScreenPacketData): Packet<*>
    fun updateAttributesPacket(data: ClientboundUpdateAttributesPacketData): Packet<*>
    fun entityEventPacket(data: ClientboundEntityEventPacketData): Packet<*>
    fun animatePacket(data: ClientboundAnimatePacketData): Packet<*>
    fun setBlockDestroyStagePacket(data: ClientboundBlockDestructionPacketData): Packet<*>
    fun openSignEditorPacket(data: ClientboundOpenSignEditorPacketData): Packet<*>
    fun blockEntityDataPacket(data: ClientboundBlockEntityDataPacketData): Packet<*>
    fun blockUpdatePacket(data: ClientboundBlockUpdatePacketData): Packet<*>
    fun equipmentPacket(data: ClientboundSetEquipmentPacketData): Packet<*>
    fun playerInfoUpdatePacket(data: ClientboundPlayerInfoUpdatePacketData): Packet<*>
    fun teleportEntityPacket(data: ClientboundTeleportEntityPacketData): Packet<*>
    fun playerInfoRemovePacket(data: ClientboundPlayerInfoRemovePacketData): Packet<*>
    fun removeEntitiesPacket(data: ClientboundRemoveEntitiesPacketData): Packet<*>
    fun addEntitiesPacket(data: ClientboundAddEntityPacketData): Packet<*>
    fun rotateHeadPacket(data: ClientboundRotateHeadPacketData): Packet<*>
    fun setEntityDataPacket(data: ClientboundSetEntityDataPacketData): Packet<*>
    fun setPassengersPacket(data: ClientboundSetPassengersPacketData): Packet<*>
}