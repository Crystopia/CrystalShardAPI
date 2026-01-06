package net.crystopia.crystalshard.extras.displays

import net.crystopia.crystalshard.extras.factories.PacketFactory
import net.crystopia.crystalshard.extras.packets.ServerboundInteractPacketUtil
import net.crystopia.crystalshard.extras.packets.ServerboundInteractPacketUtil.ClickActionType
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Interaction
import net.minecraft.world.phys.Vec3
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

open class DisplayInteraction(open var entity: Display) {
    lateinit var interaction: Interaction

    fun onHover(plugin: JavaPlugin, player: Player, detectScalar: Double? = 0.99, callback: (lock: Boolean) -> Unit) {
        Bukkit.getServer().scheduler.runTaskTimer(plugin, Runnable {
            val toEntity = interaction.bukkitEntity.location.toVector().subtract(player.eyeLocation.toVector())

            if (toEntity.normalize().dot(player.eyeLocation.direction) > detectScalar!!) {
                callback(true)
            } else {
                callback(false)
            }

        }, 1L, 1L)
    }


    fun onInteract(
        key: NamespacedKey,
        plugin: JavaPlugin,
        size: Pair<Float, Float>,
        player: Player,
        callback: (clickType: ClickActionType, msg: ServerboundInteractPacket) -> Unit
    ) {

        // Register event for the Display
        entity.bukkitEntity.persistentDataContainer.set(key, PersistentDataType.STRING, entity.uuid.toString())

        // Add the key to the interaction...
        interaction = Interaction(EntityType.INTERACTION, (player as CraftPlayer).handle.level())
        interaction.bukkitEntity.persistentDataContainer.set(
            key,
            PersistentDataType.STRING,
            entity.uuid.toString()
        )

        PacketFactory.addEntitiesPacket(
            entityId = interaction.id,
            entityUUID = interaction.uuid,
            location = entity.bukkitEntity.location,
            entityType = interaction.type,
            data = 0,
            deltaMovement = Vec3.ZERO,
            yHeadRot = 0.0,
        ) { packet ->
            PacketFactory.sendPacket(packet, mutableListOf(player))
        }
        val width = EntityDataAccessor(8, EntityDataSerializers.FLOAT)
        val height = EntityDataAccessor(9, EntityDataSerializers.FLOAT)
        val responsive = EntityDataAccessor(10, EntityDataSerializers.BOOLEAN)
        PacketFactory.setEntityDataPacket(
            entityId = interaction.id, entityData = mutableListOf(
                SynchedEntityData.DataValue.create(
                    width, size.first
                ), SynchedEntityData.DataValue.create(
                    height, size.second
                ), SynchedEntityData.DataValue.create(
                    responsive, true
                )
            )
        ) { packet ->
            PacketFactory.sendPacket(packet, mutableListOf(player))
        }

        ServerboundInteractPacketUtil.attach("${key.namespace}:${key.key}", plugin, player) { clickType, msg ->
            if (msg.entityId == interaction.id) {
                callback(clickType, msg)
            }
        }
    }
}