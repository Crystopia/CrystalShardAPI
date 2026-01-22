package net.crystopia.crystalshard.paper.dhl

import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.attributes.Attribute
import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.data.game.GameEventType
import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.MerchantOffers
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.*
import net.crystopia.crystalshard.paper.dhl.shared.data.packetsid.ClientboundSetPassengersPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.Particle
import net.crystopia.crystalshard.paper.dhl.shared.data.scoreboard.DisplayData
import net.crystopia.crystalshard.paper.dhl.shared.data.scoreboard.ScoreData
import net.crystopia.crystalshard.paper.dhl.shared.data.teams.Team
import net.crystopia.crystalshard.paper.dhl.shared.data.waypoints.TrackedWaypoint
import net.crystopia.crystalshard.paper.dhl.shared.data.world.WorldBorder
import net.crystopia.crystalshard.paper.dhl.shared.enums.blocks.BlockEntityType
import net.crystopia.crystalshard.paper.dhl.shared.enums.blocks.BlockType
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityType
import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.EquipmentSlot
import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.MenuType
import net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.paper.dhl.shared.enums.player.GameMode
import net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.DisplaySlot
import net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.ScoreBoardMode
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.TeamAction
import net.crystopia.crystalshard.paper.dhl.shared.enums.waypoints.WaypointOperation
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder
import net.kyori.adventure.text.Component
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.common.ClientboundCustomReportDetailsPacket
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket
import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

object PacketFactory {

    fun sendTeam(
        action: TeamAction,
        team: Team,
        callback: (packet: Shard_Packet<ClientboundSetPlayerTeamPacketData>) -> Unit
    ): Shard_Packet<ClientboundSetPlayerTeamPacketData> {

        val data = ClientboundSetPlayerTeamPacketData(
            action, team
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.sendTeam(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.sendTeam(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundSetPlayerTeamPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setMerchantOffer(
        windowId: Int,
        merchantOffers: MerchantOffers,
        levelProgress: Int,
        experience: Int,
        leveled: Boolean,
        refreshable: Boolean,
        callback: (packet: Shard_Packet<ClientboundMerchantOffersPacketData>) -> Unit
    ): Shard_Packet<ClientboundMerchantOffersPacketData> {

        val data = ClientboundMerchantOffersPacketData(
            windowId, merchantOffers, levelProgress, experience, leveled, refreshable
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setMerchantOffer(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setMerchantOffer(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundMerchantOffersPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun spawnParticle(
        particle: Particle<*, *>,
        callback: (packet: Shard_Packet<ClientboundLevelParticlesPacketData>) -> Unit
    ): Shard_Packet<ClientboundLevelParticlesPacketData> {

        val data = ClientboundLevelParticlesPacketData(
            particle
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.spawnParticle(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.spawnParticle(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundLevelParticlesPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setWorldBorderWarningDistance(
        border: WorldBorder,
        callback: (packet: Shard_Packet<ClientboundBorderPacketData>) -> Unit
    ): Shard_Packet<ClientboundBorderPacketData> {

        val data = ClientboundBorderPacketData(
            border
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setWorldBorderWarningDistance(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setWorldBorderWarningDistance(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundBorderPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setWorldBorderWarningDelay(
        border: WorldBorder,
        callback: (packet: Shard_Packet<ClientboundBorderPacketData>) -> Unit
    ): Shard_Packet<ClientboundBorderPacketData> {

        val data = ClientboundBorderPacketData(
            border
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setWorldBorderWarningDelay(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setWorldBorderWarningDelay(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundBorderPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setWorldBorderLerpSize(
        border: WorldBorder,
        callback: (packet: Shard_Packet<ClientboundBorderPacketData>) -> Unit
    ): Shard_Packet<ClientboundBorderPacketData> {

        val data = ClientboundBorderPacketData(
            border
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setWorldBorderLerpSize(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setWorldBorderLerpSize(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundBorderPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setWorldBorderCenter(
        border: WorldBorder,
        callback: (packet: Shard_Packet<ClientboundBorderPacketData>) -> Unit
    ): Shard_Packet<ClientboundBorderPacketData> {

        val data = ClientboundBorderPacketData(
            border
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setWorldBorderCenter(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setWorldBorderCenter(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundBorderPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setWorldBorderSize(
        border: WorldBorder,
        callback: (packet: Shard_Packet<ClientboundBorderPacketData>) -> Unit
    ): Shard_Packet<ClientboundBorderPacketData> {

        val data = ClientboundBorderPacketData(
            border
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setWorldBorderSize(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setWorldBorderSize(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundBorderPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun initWorldBorder(
        border: WorldBorder,
        callback: (packet: Shard_Packet<ClientboundBorderPacketData>) -> Unit
    ): Shard_Packet<ClientboundBorderPacketData> {

        val data = ClientboundBorderPacketData(
            border
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.initWorldBorder(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.initWorldBorder(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundBorderPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun runGameEvent(
        type: GameEventType,
        /**
         * More about action: https://minecraft.wiki/w/Java_Edition_protocol/Packets#Game_Event
         */
        action: Float,
        callback: (packet: Shard_Packet<ClientboundGameEventPacketData>) -> Unit
    ): Shard_Packet<ClientboundGameEventPacketData> {

        val data = ClientboundGameEventPacketData(
            type, action
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.runGameEvent(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.runGameEvent(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundGameEventPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun playRespawnPacket(
        world: World,
        deathLocation: Location,
        gameMode: GameMode,
        isDebug: Boolean,
        isFlat: Boolean,
        portalCooldown: Int,
        datakept: Byte,
        callback: (packet: Shard_Packet<ClientboundRespawnPacketData>) -> Unit
    ): Shard_Packet<ClientboundRespawnPacketData> {

        val data = ClientboundRespawnPacketData(
            world, deathLocation, gameMode, isDebug, isFlat, portalCooldown, datakept
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.playRespawnPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.playRespawnPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundRespawnPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun resetScoreInDisplayObject(
        score: ScoreData<*>,
        callback: (packet: Shard_Packet<ClientboundSetScorePacketData>) -> Unit
    ): Shard_Packet<ClientboundSetScorePacketData> {

        val data = ClientboundSetScorePacketData(
            score
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.resetScoreInDisplayObject(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.resetScoreInDisplayObject(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundSetScorePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setScoreInDisplayObject(
        score: ScoreData<*>,
        callback: (packet: Shard_Packet<ClientboundSetScorePacketData>) -> Unit
    ): Shard_Packet<ClientboundSetScorePacketData> {

        val data = ClientboundSetScorePacketData(
            score
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setScoreInDisplayObject(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setScoreInDisplayObject(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundSetScorePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun sendObjectiveUpdate(
        mode: ScoreBoardMode,
        displaySlot: DisplaySlot,
        displayData: DisplayData<*>,
        callback: (packet: Shard_Packet<ClientboundSetDisplayObjectivePacketData>) -> Unit
    ): Shard_Packet<ClientboundSetDisplayObjectivePacketData> {

        val data = ClientboundSetDisplayObjectivePacketData(
            mode, displaySlot, displayData,
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.sendObjectiveUpdate(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.sendObjectiveUpdate(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundSetDisplayObjectivePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setDisplayObjective(
        mode: ScoreBoardMode,
        displaySlot: DisplaySlot,
        displayData: DisplayData<*>,
        callback: (packet: Shard_Packet<ClientboundSetDisplayObjectivePacketData>) -> Unit
    ): Shard_Packet<ClientboundSetDisplayObjectivePacketData> {

        val data = ClientboundSetDisplayObjectivePacketData(
            ScoreBoardMode.CREATE, displaySlot, displayData,
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setDisplayObjective(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setDisplayObjective(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundSetDisplayObjectivePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setHealth(
        health: Float,
        food: Int,
        saturation: Float,
        callback: (packet: Shard_Packet<ClientboundSetHealthPacketData>) -> Unit
    ): Shard_Packet<ClientboundSetHealthPacketData> {

        val data = ClientboundSetHealthPacketData(
            health, food, saturation
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setHealth(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setHealth(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundSetHealthPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setItemOnCursor(
        /**
         * 1.21.10+
         */
        item: ItemStack,
        /**
         * 1.21.1
         */
        slot: Int, callback: (packet: Shard_Packet<ClientboundSetCarriedItemPacketData>) -> Unit
    ): Shard_Packet<ClientboundSetCarriedItemPacketData> {

        val data = ClientboundSetCarriedItemPacketData(
            item, slot
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setCarriedItem(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setCarriedItem(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundSetCarriedItemPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun applyCooldown(
        item: Material, duration: Int, callback: (packet: Shard_Packet<ClientboundCooldownPacketData>) -> Unit
    ): Shard_Packet<ClientboundCooldownPacketData> {

        val data = ClientboundCooldownPacketData(
            item, duration
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.applyCooldown(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.applyCooldown(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundCooldownPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun sendPlayerCombatKillPacket(
        entityId: Int,
        message: Component,
        callback: (packet: Shard_Packet<ClientboundPlayerCombatKillPacketData>) -> Unit
    ): Shard_Packet<ClientboundPlayerCombatKillPacketData> {

        val data = ClientboundPlayerCombatKillPacketData(
            entityId, message
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.sendPlayerCombatKillPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.sendPlayerCombatKillPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundPlayerCombatKillPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun sendWaypoint(
        operation: WaypointOperation,
        waypoints: TrackedWaypoint<*>,
        callback: (packet: Shard_Packet<ClientboundTrackedWaypointPacketData>) -> Unit
    ): Shard_Packet<ClientboundTrackedWaypointPacketData> {

        val data = ClientboundTrackedWaypointPacketData(
            operation, waypoints
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.sendWaypointPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                throw Exception("Waypoint Packets are not implemented in this Version!")
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundTrackedWaypointPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setContainerData(
        id: Int,
        property: Short,
        value: Short,
        callback: (packet: Shard_Packet<ClientboundContainerSetDataPacketData>) -> Unit
    ): Shard_Packet<ClientboundContainerSetDataPacketData> {

        val data = ClientboundContainerSetDataPacketData(
            id, property, value
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setContainerData(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setContainerData(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundContainerSetDataPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setContainerContent(
        id: Int,
        stateId: Int,
        items: MutableList<ItemStack>,
        carriedItem: ItemStack,
        callback: (packet: Shard_Packet<ClientboundContainerSetContentPacketData>) -> Unit
    ): Shard_Packet<ClientboundContainerSetContentPacketData> {

        val data = ClientboundContainerSetContentPacketData(
            id, stateId, items, carriedItem
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setContainerContent(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setContainerContent(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundContainerSetContentPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setContainerSlot(
        id: Int,
        revision: Int,
        slot: Int,
        item: ItemStack,
        callback: (packet: Shard_Packet<ClientboundContainerSetSlotPacketData>) -> Unit
    ): Shard_Packet<ClientboundContainerSetSlotPacketData> {

        val data = ClientboundContainerSetSlotPacketData(
            id, revision, slot, item
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setContainerSlot(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setContainerSlot(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundContainerSetSlotPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun closeContainerPacket(
        id: Int, callback: (packet: Shard_Packet<ClientboundContainerClosePacketData>) -> Unit
    ): Shard_Packet<ClientboundContainerClosePacketData> {

        val data = ClientboundContainerClosePacketData(
            id
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.closeContainerPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.closeContainerPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundContainerClosePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun openScreenPacket(
        id: Int,
        title: Component,
        type: MenuType,
        callback: (packet: Shard_Packet<ClientboundOpenScreenPacketData>) -> Unit
    ): Shard_Packet<ClientboundOpenScreenPacketData> {

        val data = ClientboundOpenScreenPacketData(
            id, type, title
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.openScreenPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.openScreenPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundOpenScreenPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun updateAttributesPacket(
        entityId: Int,
        /**
         * See more infos about status. [Entity_statuses](https://minecraft.wiki/w/Java_Edition_protocol/Entity_statuses)
         */
        attributes: MutableList<Attribute>,
        callback: (packet: Shard_Packet<ClientboundUpdateAttributesPacketData>) -> Unit
    ): Shard_Packet<ClientboundUpdateAttributesPacketData> {

        val data = ClientboundUpdateAttributesPacketData(
            entityId, attributes
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.updateAttributesPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.updateAttributesPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundUpdateAttributesPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun sendEntityEventPacket(
        entityId: Int,
        /**
         * See more infos about status. [Entity_statuses](https://minecraft.wiki/w/Java_Edition_protocol/Entity_statuses)
         */
        status: Byte,
        world: World,
        callback: (packet: Shard_Packet<ClientboundEntityEventPacketData>) -> Unit
    ): Shard_Packet<ClientboundEntityEventPacketData> {

        val data = ClientboundEntityEventPacketData(
            entityId, status, world
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.entityEventPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.entityEventPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundEntityEventPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createAnimatePacket(
        entityId: Int, animationId: Int, callback: (packet: Shard_Packet<ClientboundAnimatePacketData>) -> Unit
    ): Shard_Packet<ClientboundAnimatePacketData> {

        val data = ClientboundAnimatePacketData(
            entityId, animationId
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.animatePacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.animatePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundAnimatePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createBlockDestroyStagePacket(
        entityId: Int, pos: BlockPos,
        /**
         * Read more: [Packets#Block_Entity_Data](https://minecraft.wiki/w/Java_Edition_protocol/Packets#Block_Entity_Data)
         */
        progress: Int, callback: (packet: Shard_Packet<ClientboundBlockDestructionPacketData>) -> Unit
    ): Shard_Packet<ClientboundBlockDestructionPacketData> {

        val data = ClientboundBlockDestructionPacketData(
            entityId, pos, progress
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setBlockDestroyStagePacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setBlockDestroyStagePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundBlockDestructionPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createOpenSignEditorPacket(
        blockPos: BlockPos,
        isFrontText: Boolean,
        callback: (packet: Shard_Packet<ClientboundOpenSignEditorPacketData>) -> Unit
    ): Shard_Packet<ClientboundOpenSignEditorPacketData> {

        val data = ClientboundOpenSignEditorPacketData(
            blockPos, isFrontText
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.openSignEditorPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.openSignEditorPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundOpenSignEditorPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createBlockEntityDataPacket(
        blockPos: BlockPos,
        type: BlockEntityType,
        nbt: CompoundTag,
        callback: (packet: Shard_Packet<ClientboundBlockEntityDataPacketData>) -> Unit
    ): Shard_Packet<ClientboundBlockEntityDataPacketData> {

        val data = ClientboundBlockEntityDataPacketData(
            blockPos, type, nbt
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.blockEntityDataPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.blockEntityDataPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundBlockEntityDataPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createBlockUpdatePacket(
        pos: BlockPos,
        state: BlockType,
        callback: (packet: Shard_Packet<ClientboundBlockUpdatePacketData>) -> Unit
    ): Shard_Packet<ClientboundBlockUpdatePacketData> {

        val data = ClientboundBlockUpdatePacketData(
            pos, state
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.blockUpdatePacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.blockUpdatePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundBlockUpdatePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createEquipmentPacket(
        entityId: Int,
        equipmentList: MutableList<Pair<EquipmentSlot, ItemStack>>,
        callback: (packet: Shard_Packet<ClientboundSetEquipmentPacketData>) -> Unit
    ): Shard_Packet<ClientboundSetEquipmentPacketData> {

        val data = ClientboundSetEquipmentPacketData(
            entityId, equipmentList
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.equipmentPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.equipmentPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        val shardPacket = Shard_Packet<ClientboundSetEquipmentPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun playerInfoUpdatePacket(
        serverPlayer: Player,
        actions: EnumSet<InfoUpdateAction>,
        callback: (packet: Shard_Packet<ClientboundPlayerInfoUpdatePacketData>) -> Unit
    ): Shard_Packet<ClientboundPlayerInfoUpdatePacketData> {

        val data = ClientboundPlayerInfoUpdatePacketData(
            serverPlayer, actions
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.playerInfoUpdatePacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.playerInfoUpdatePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        val shardPacket = Shard_Packet<ClientboundPlayerInfoUpdatePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun teleportEntityPacket(
        entityId: Int,
        location: Location,
        onGround: Boolean,
        callback: (packet: Shard_Packet<ClientboundTeleportEntityPacketData>) -> Unit
    ): Shard_Packet<ClientboundTeleportEntityPacketData> {

        val data = ClientboundTeleportEntityPacketData(
            entityId, location, onGround
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.teleportEntityPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.teleportEntityPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        val shardPacket = Shard_Packet<ClientboundTeleportEntityPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun playerInfoRemovePacket(
        uuids: MutableList<UUID>, callback: (packet: Shard_Packet<ClientboundPlayerInfoRemovePacketData>) -> Unit
    ): Shard_Packet<ClientboundPlayerInfoRemovePacketData> {

        val data = ClientboundPlayerInfoRemovePacketData(
            uuids
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.playerInfoRemovePacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.playerInfoRemovePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        val shardPacket = Shard_Packet<ClientboundPlayerInfoRemovePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun removeEntitiesPacket(
        entityIds: List<Int>, callback: (packet: Shard_Packet<ClientboundRemoveEntitiesPacketData>) -> Unit
    ): Shard_Packet<ClientboundRemoveEntitiesPacketData> {

        val data = ClientboundRemoveEntitiesPacketData(
            entityIds
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.removeEntitiesPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.removeEntitiesPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        val shardPacket = Shard_Packet<ClientboundRemoveEntitiesPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun addEntitiesPacket(
        entityId: Int,
        entityUUID: UUID,
        location: Location,
        entityType: EntityType,
        data: Int,
        yHeadRot: Double = 0.0,
        callback: (packet: Shard_Packet<ClientboundAddEntityPacketData>) -> Unit
    ): Shard_Packet<ClientboundAddEntityPacketData> {

        val data = ClientboundAddEntityPacketData(
            entityId, entityUUID, location, entityType, data, yHeadRot
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.addEntitiesPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.addEntitiesPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundAddEntityPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun rotateHeadPacket(
        entityId: Int, yaw: Float, callback: (packet: Shard_Packet<ClientboundRotateHeadPacketData>) -> Unit
    ): Shard_Packet<ClientboundRotateHeadPacketData> {

        val data = ClientboundRotateHeadPacketData(
            entityId, yaw
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.rotateHeadPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.rotateHeadPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundRotateHeadPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    /**
     * Read more about [Entity_Metadata_Format](https://minecraft.wiki/w/Java_Edition_protocol/Entity_metadata#Entity_Metadata_Format)
     *
     */
    fun setEntityDataPacket(
        entityId: Int,
        entityData: MutableList<EntityMetadata<*>>,
        callback: (packet: Shard_Packet<ClientboundSetEntityDataPacketData>) -> Unit
    ): Shard_Packet<ClientboundSetEntityDataPacketData> {

        val data = ClientboundSetEntityDataPacketData(
            entityId, entityData
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setEntityDataPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setEntityDataPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        val shardPacket = Shard_Packet<ClientboundSetEntityDataPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet

        callback(shardPacket)
        return shardPacket
    }

    fun setPassengersPacket(
        entity: Entity,
        passengers: MutableList<Entity>,
        callback: (packet: Shard_Packet<ClientboundSetPassengersPacketData>) -> Unit
    ): Shard_Packet<ClientboundSetPassengersPacketData> {

        val data = ClientboundSetPassengersPacketData(
            entity, passengers
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setPassengersPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setPassengersPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundSetPassengersPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    /**
     *
     */
    fun sendPacket(packet: Packet<*>, players: MutableList<Player>) {
        players.forEach { player ->
            val serverPlayer = (player as CraftPlayer).handle
            serverPlayer.connection.send(packet)
        }
    }

}