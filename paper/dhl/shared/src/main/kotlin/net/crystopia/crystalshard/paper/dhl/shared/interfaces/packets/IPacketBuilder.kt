package net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundAddEntityPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundAnimatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundBlockDestructionPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundBlockEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundBlockUpdatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundBorderPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundContainerClosePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundContainerSetContentPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundContainerSetDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundContainerSetSlotPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundCooldownPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundEntityEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundEntityPositionSyncPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundGameEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundLevelEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundLevelParticlesPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMapItemDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMerchantOffersPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMoveEntityPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMoveMinecartPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMoveVehiclePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundOpenScreenPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundOpenSignEditorPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundPlayerCombatKillPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundPlayerInfoRemovePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundPlayerInfoUpdatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundPlayerLookAtPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundPlayerPositionPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundPlayerRotationPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundRemoveEntitiesPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundRemoveMobEffectPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundRespawnPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundRotateHeadPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetCarriedItemPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetDefaultSpawnPositionPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetDisplayObjectivePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetEquipmentPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetHealthPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetPlayerTeamPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetScorePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetTimePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundShowDialogPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundTabListPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundTeleportEntityPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundTickingStatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundTickingStepPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundTrackedWaypointPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundUpdateAttributesPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundUpdateMobEffectPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packetsid.ClientboundSetPassengersPacketData
import net.minecraft.network.protocol.Packet

interface IPacketBuilder {

    fun showDialog(data: ClientboundShowDialogPacketData): Packet<*>
    fun updateTickingState(data: ClientboundTickingStatePacketData): Packet<*>
    fun updateTickingStep(data: ClientboundTickingStepPacketData): Packet<*>
    fun setTime(data: ClientboundSetTimePacketData): Packet<*>
    fun setTabList(data: ClientboundTabListPacketData): Packet<*>
    fun setDefaultSpawnPosition(data: ClientboundSetDefaultSpawnPositionPacketData): Packet<*>
    fun sendLevelEvent(data: ClientboundLevelEventPacketData): Packet<*>
    fun setMapItemData(data: ClientboundMapItemDataPacketData): Packet<*>
    fun moveVehicle(data: ClientboundMoveVehiclePacketData): Packet<*>
    fun moveMinecart(data: ClientboundMoveMinecartPacketData): Packet<*>
    fun moveEntity(data: ClientboundMoveEntityPacketData): Packet<*>
    fun updateEntityPositionSync(data: ClientboundEntityPositionSyncPacketData): Packet<*>
    fun updatePlayerLookAt(data: ClientboundPlayerLookAtPacketData): Packet<*>
    fun updatePlayerPosition(data: ClientboundPlayerPositionPacketData): Packet<*>
    fun updatePlayerRotation(data: ClientboundPlayerRotationPacketData): Packet<*>
    fun removeMobEffect(data: ClientboundRemoveMobEffectPacketData): Packet<*>
    fun applyMobEffect(data: ClientboundUpdateMobEffectPacketData): Packet<*>
    fun sendTeam(data: ClientboundSetPlayerTeamPacketData): Packet<*>
    fun setMerchantOffer(data: ClientboundMerchantOffersPacketData): Packet<*>
    fun spawnParticle(data: ClientboundLevelParticlesPacketData): Packet<*>
    fun setWorldBorderSize(data: ClientboundBorderPacketData): Packet<*>
    fun setWorldBorderCenter(data: ClientboundBorderPacketData): Packet<*>
    fun setWorldBorderLerpSize(data: ClientboundBorderPacketData): Packet<*>
    fun setWorldBorderWarningDelay(data: ClientboundBorderPacketData): Packet<*>
    fun setWorldBorderWarningDistance(data: ClientboundBorderPacketData): Packet<*>
    fun initWorldBorder(data: ClientboundBorderPacketData): Packet<*>
    fun runGameEvent(data: ClientboundGameEventPacketData): Packet<*>
    fun playRespawnPacket(data: ClientboundRespawnPacketData): Packet<*>
    fun resetScoreInDisplayObject(data: ClientboundSetScorePacketData): Packet<*>
    fun setScoreInDisplayObject(data: ClientboundSetScorePacketData): Packet<*>
    fun sendObjectiveUpdate(data: ClientboundSetDisplayObjectivePacketData): Packet<*>
    fun setDisplayObjective(data: ClientboundSetDisplayObjectivePacketData): Packet<*>
    fun setHealth(data: ClientboundSetHealthPacketData): Packet<*>
    fun setCarriedItem(data: ClientboundSetCarriedItemPacketData): Packet<*>
    fun applyCooldown(data: ClientboundCooldownPacketData): Packet<*>
    fun sendPlayerCombatKillPacket(data: ClientboundPlayerCombatKillPacketData): Packet<*>
    fun sendWaypointPacket(data: ClientboundTrackedWaypointPacketData): Packet<*>
    fun setContainerData(data: ClientboundContainerSetDataPacketData): Packet<*>
    fun setContainerContent(data: ClientboundContainerSetContentPacketData): Packet<*>
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