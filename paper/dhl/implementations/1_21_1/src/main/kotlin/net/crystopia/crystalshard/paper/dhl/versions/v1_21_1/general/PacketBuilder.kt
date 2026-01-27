package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general

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
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacketBuilder
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets.*
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.*

object PacketBuilder : IPacketBuilder {

    override fun showDialog(data: ClientboundShowDialogPacketData): Packet<*> {
        throw Exception("Move Minecart Packet not available on 1.21.1")
    }

    override fun updateTickingState(data: ClientboundTickingStatePacketData): Packet<*> {
        return Shard_ClientboundTickingStatePacket().createPacket(data)
    }

    override fun updateTickingStep(data: ClientboundTickingStepPacketData): Packet<*> {
        return Shard_ClientboundTickingStepPacket().createPacket(data)
    }

    override fun setTime(data: ClientboundSetTimePacketData): Packet<*> {
        return Shard_ClientboundSetTimePacket().createPacket(data)
    }

    override fun setTabList(data: ClientboundTabListPacketData): Packet<*> {
        return Shard_ClientboundTabListPacket().createPacket(data)
    }

    override fun setDefaultSpawnPosition(data: ClientboundSetDefaultSpawnPositionPacketData): Packet<*> {
        return Shard_ClientboundSetDefaultSpawnPositionPacket().createPacket(data)
    }

    override fun sendLevelEvent(data: ClientboundLevelEventPacketData): Packet<*> {
        return Shard_ClientboundLevelEventPacket().createPacket(data)
    }

    override fun setMapItemData(data: ClientboundMapItemDataPacketData): Packet<*> {
        return Shard_ClientboundMapItemDataPacket().createPacket(data)
    }

    override fun moveVehicle(data: ClientboundMoveVehiclePacketData): Packet<*> {
        return Shard_ClientboundMoveVehiclePacket().createPacket(data)
    }

    override fun moveMinecart(data: ClientboundMoveMinecartPacketData): Packet<*> {
        throw Exception("Move Minecart Packet not available on 1.21.1")
    }

    override fun moveEntity(data: ClientboundMoveEntityPacketData): Packet<*> {
        return Shard_ClientboundMoveEntityPacket().createPacket(data)
    }

    override fun updateEntityPositionSync(data: ClientboundEntityPositionSyncPacketData): Packet<*> {
        throw Exception("Entity Position Sync not available on 1.21.1")
    }

    override fun updatePlayerLookAt(data: ClientboundPlayerLookAtPacketData): Packet<*> {
        return Shard_ClientboundPlayerLookAtPacket().createPacket(data)
    }

    override fun updatePlayerPosition(data: ClientboundPlayerPositionPacketData): Packet<*> {
        return Shard_ClientboundPlayerPositionPacket().createPacket(data)
    }

    override fun updatePlayerRotation(data: ClientboundPlayerRotationPacketData): Packet<*> {
        throw Exception("Rotation Packet not available on 1.21.1")
    }

    override fun removeMobEffect(data: ClientboundRemoveMobEffectPacketData): Packet<*> {
        return Shard_ClientboundRemoveMobEffectPacket().createPacket(data)
    }

    override fun applyMobEffect(data: ClientboundUpdateMobEffectPacketData): Packet<*> {
        return Shard_ClientboundUpdateMobEffectPacket().createPacket(data)
    }

    override fun sendTeam(data: ClientboundSetPlayerTeamPacketData): Packet<*> {
        return Shard_ClientboundSetPlayerTeamPacket().createPacket(data)
    }

    override fun setMerchantOffer(data: ClientboundMerchantOffersPacketData): Packet<*> {
        return Shard_ClientboundMerchantOffersPacket().createPacket(data)
    }

    override fun spawnParticle(data: ClientboundLevelParticlesPacketData): Packet<*> {
        return Shard_ClientboundLevelParticlesPacket().createPacket(data)
    }

    override fun setWorldBorderSize(data: ClientboundBorderPacketData): Packet<*> {
        return Shard_ClientboundSetBorderSizePacket().createPacket(data)
    }

    override fun setWorldBorderCenter(data: ClientboundBorderPacketData): Packet<*> {
        return Shard_ClientboundSetBorderCenterPacket().createPacket(data)
    }

    override fun setWorldBorderLerpSize(data: ClientboundBorderPacketData): Packet<*> {
        return Shard_ClientboundSetBorderLerpSizePacket().createPacket(data)
    }

    override fun setWorldBorderWarningDelay(data: ClientboundBorderPacketData): Packet<*> {
        return Shard_ClientboundSetBorderWarningDelayPacket().createPacket(data)
    }

    override fun setWorldBorderWarningDistance(data: ClientboundBorderPacketData): Packet<*> {
        return Shard_ClientboundSetBorderWarningDistancePacket().createPacket(data)
    }

    override fun initWorldBorder(data: ClientboundBorderPacketData): Packet<*> {
        return Shard_ClientboundInitializeBorderPacket().createPacket(data)
    }

    override fun runGameEvent(data: ClientboundGameEventPacketData): Packet<*> {
        return Shard_ClientboundGameEventPacket().createPacket(data)
    }

    override fun playRespawnPacket(data: ClientboundRespawnPacketData): Packet<*> {
        return Shard_ClientboundRespawnPacket().createPacket(data)
    }

    override fun resetScoreInDisplayObject(data: ClientboundSetScorePacketData): Packet<*> {
        return Shard_ClientboundResetScorePacket().createPacket(data)
    }

    override fun setScoreInDisplayObject(data: ClientboundSetScorePacketData): Packet<*> {
        return Shard_ClientboundSetScorePacket().createPacket(data)
    }

    override fun sendObjectiveUpdate(data: ClientboundSetDisplayObjectivePacketData): Packet<*> {
        return Shard_ClientboundSetObjectivePacket().createPacket(data)
    }

    override fun setDisplayObjective(data: ClientboundSetDisplayObjectivePacketData): Packet<*> {
        return Shard_ClientboundSetDisplayObjectivePacket().createPacket(data)
    }

    override fun setHealth(data: ClientboundSetHealthPacketData): Packet<*> {
        return Shard_ClientboundSetHealthPacket().createPacket(data)
    }

    override fun setCarriedItem(data: ClientboundSetCarriedItemPacketData): Packet<*> {
        return Shard_ClientboundSetCarriedItemPacket().createPacket(data)
    }

    override fun applyCooldown(data: ClientboundCooldownPacketData): Packet<*> {
        return Shard_ClientboundCooldownPacket().createPacket(data)
    }

    override fun sendPlayerCombatKillPacket(data: ClientboundPlayerCombatKillPacketData): Packet<*> {
        return Shard_ClientboundPlayerCombatKillPacket().createPacket(data)
    }

    override fun sendWaypointPacket(data: ClientboundTrackedWaypointPacketData): Packet<*> {
        throw Exception("Waypoints are not supported in 1.21.1")
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