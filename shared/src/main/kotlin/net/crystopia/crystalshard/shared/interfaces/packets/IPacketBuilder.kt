package net.crystopia.crystalshard.shared.interfaces.packets

import net.crystopia.crystalshard.shared.data.packets.*
import net.minecraft.network.protocol.Packet

interface IPacketBuilder {

    fun  equipmentPacket(data: ClientboundSetEquipmentPacketData): Packet<*>
    fun playerInfoUpdatePacket(data: ClientboundPlayerInfoUpdatePacketData): Packet<*>
    fun teleportEntityPacket(data: ClientboundTeleportEntityPacketData): Packet<*>
    fun playerInfoRemovePacket(data: ClientboundPlayerInfoRemovePacketData): Packet<*>
    fun removeEntitiesPacket(data: ClientboundRemoveEntitiesPacketData): Packet<*>
    fun addEntitiesPacket(data: ClientboundAddEntityPacketData): Packet<*>
    fun rotateHeadPacket(data: ClientboundRotateHeadPacketData): Packet<*>
    fun setEntityDataPacket(data: ClientboundSetEntityDataPacketData): Packet<*>
    fun setPassengersPacket(data: ClientboundSetPassengersPacketData): Packet<*>
}