package net.crystopia.crystalshard.extras.factories

import com.mojang.authlib.GameProfile
import com.mojang.datafixers.util.Pair
import net.crystopia.crystalshard.common.utils.ServerUtil
import net.crystopia.crystalshard.shared.data.packets.*
import net.crystopia.crystalshard.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.versions.v1_21_10.general.PacketBuilder
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.Vec3
import org.bukkit.Location
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import java.util.*

object PacketFactory {


    fun createEquipmentPacket(
        entityId: Int, equipmentList: MutableList<Pair<EquipmentSlot, ItemStack>>, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

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
                net.crystopia.crystalshard.versions.v1_21_1.general.PacketBuilder.equipmentPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun playerInfoUpdatePacket(
        serverPlayer: ServerPlayer,
        gameProfile: GameProfile,
        actions: EnumSet<InfoUpdateAction>,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundPlayerInfoUpdatePacketData(
            serverPlayer, gameProfile, actions
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.playerInfoUpdatePacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.versions.v1_21_1.general.PacketBuilder.playerInfoUpdatePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun teleportEntityPacket(
        entityId: Int, location: Location, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundTeleportEntityPacketData(
            entityId, location
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.teleportEntityPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.versions.v1_21_1.general.PacketBuilder.teleportEntityPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun playerInfoRemovePacket(
        uuids: MutableList<UUID>, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

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
                net.crystopia.crystalshard.versions.v1_21_1.general.PacketBuilder.playerInfoRemovePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun removeEntitiesPacket(
        entityIds: List<Int>, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

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
                net.crystopia.crystalshard.versions.v1_21_1.general.PacketBuilder.removeEntitiesPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun addEntitiesPacket(
        entityId: Int,
        entityUUID: UUID,
        location: Location,
        entityType: EntityType<*>,
        data: Int,
        deltaMovement: Vec3 = Vec3.ZERO,
        yHeadRot: Double = 0.0,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundAddEntityPacketData(
            entityId, entityUUID, location, entityType, data, deltaMovement, yHeadRot
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.addEntitiesPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.versions.v1_21_1.general.PacketBuilder.addEntitiesPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun rotateHeadPacket(
        entity: Entity, yaw: Float, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundRotateHeadPacketData(
            entity, yaw
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.rotateHeadPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.versions.v1_21_1.general.PacketBuilder.rotateHeadPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    /**
     * Read more about [Entity_Metadata_Format](https://minecraft.wiki/w/Java_Edition_protocol/Entity_metadata#Entity_Metadata_Format)
     *
     * ```
     * // Use the 6 for the Index.
     * val accessor = EntityDataAccessor<Pose>(6, EntityDataSerializers.POSE)
     *                 PacketFactory.setEntityDataPacket(
     *                     playerEntity.id, mutableListOf(
     *                         SynchedEntityData.DataValue.create(
     *                             accessor,
     *                             Pose.SLEEPING
     *                         )
     *                     )
     *                 ) { packet ->
     *                     PacketFactory.sendPacket(packet, CrystalShard.plugin.server.onlinePlayers.toMutableList())
     *                 }
     * ```
     */

    fun setEntityDataPacket(
        entityId: Int, entityData: MutableList<SynchedEntityData.DataValue<*>>, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

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
                net.crystopia.crystalshard.versions.v1_21_1.general.PacketBuilder.setEntityDataPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun setPassengersPacket(
        entity: Entity, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundSetPassengersPacketData(
            entity
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setPassengersPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.versions.v1_21_1.general.PacketBuilder.setPassengersPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun sendPacket(packet: Packet<*>, players: MutableList<Player>) {
        players.forEach { player ->
            val serverPlayer = (player as CraftPlayer).handle
            serverPlayer.connection.send(packet)
        }
    }

}