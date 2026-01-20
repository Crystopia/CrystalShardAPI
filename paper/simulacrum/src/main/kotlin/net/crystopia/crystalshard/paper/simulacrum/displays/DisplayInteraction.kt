package net.crystopia.crystalshard.paper.simulacrum.displays

import net.crystopia.crystalshard.paper.dhl.PacketFactory
import net.crystopia.crystalshard.paper.dhl.server.ServerboundInteractPacketUtil
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Interaction
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

open class DisplayInteraction<T : org.bukkit.entity.Display>(open var entity: T) {
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
        callback: ServerboundInteractPacketUtil.InteractEvent.() -> Unit
    ) {

        // Register event for the Display
        entity.persistentDataContainer.set(key, PersistentDataType.STRING, entity.uniqueId.toString())

        // Add the key to the interaction...
        interaction = Interaction(EntityType.INTERACTION, (player as CraftPlayer).handle.level())
        interaction.bukkitEntity.persistentDataContainer.set(
            key,
            PersistentDataType.STRING,
            entity.uniqueId.toString()
        )

        PacketFactory.addEntitiesPacket(
            entityId = interaction.id,
            entityUUID = interaction.uuid,
            location = entity.location,
            entityType = net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityType.INTERACTION,
            data = 0,
            yHeadRot = 0.0,
        ) { packet ->
            packet.send(mutableListOf(player))
        }


        PacketFactory.setEntityDataPacket(
            interaction.id, mutableListOf(
                EntityMetadata(
                    index = 8,
                    type = EntityDataSerializerType.FLOAT,
                    value = size.first
                ),
                EntityMetadata(
                    index = 9,
                    type = EntityDataSerializerType.FLOAT,
                    value = size.second
                ),
                EntityMetadata(
                    index = 10,
                    type = EntityDataSerializerType.BOOLEAN,
                    value = true
                )
            )
        ) { packet ->
            packet.send(mutableListOf(player))
        }


        ServerboundInteractPacketUtil.attach("${key.namespace}:${key.key}", plugin, player) {
            if (entityId == interaction.id) {
                callback(this)
            }
        }

    }
}