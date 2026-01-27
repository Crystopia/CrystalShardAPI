package net.crystopia.crystalshard.paper.simulacrum.displays

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType
import net.crystopia.crystalshard.paper.simulacrum.SimulacrumFactory
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Display
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

open class DisplayInteraction<T : Display>(open var entity: T) {
    lateinit var interaction: org.bukkit.entity.Interaction

    fun onHover(plugin: JavaPlugin, player: Player, detectScalar: Double? = 0.99, callback: (lock: Boolean) -> Unit) {
        Bukkit.getServer().scheduler.runTaskTimer(plugin, Runnable {
            val toEntity = interaction.location.toVector().subtract(player.eyeLocation.toVector())

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
        // TODO: NEW SERVER PACKETS
       // callback: ServerboundInteractPacketUtil.InteractEvent.() -> Unit
    ) {

        // Register event for the Display
        entity.persistentDataContainer.set(key, PersistentDataType.STRING, entity.uniqueId.toString())

        // Add the key to the interaction...
        SimulacrumFactory.createEntityInstance<org.bukkit.entity.Interaction>(
            type = org.bukkit.entity.EntityType.INTERACTION,
            location = (player as CraftPlayer).location,
        ) {
            interaction = this
        }

        interaction.persistentDataContainer.set(
            key,
            PersistentDataType.STRING,
            entity.uniqueId.toString()
        )

        ClientPacketFactory.addEntitiesPacket(
            entityId = interaction.entityId,
            entityUUID = interaction.uniqueId,
            location = entity.location,
            entityType = org.bukkit.entity.EntityType.INTERACTION,
            data = 0,
            yHeadRot = 0.0,
        ) { packet ->
            packet.send(mutableListOf(player))
        }


        ClientPacketFactory.setEntityDataPacket(
            interaction.entityId, mutableListOf(
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

        // TODO: NEW SERVER PACKETS
        /*
        ServerboundInteractPacketUtil.attach("${key.namespace}:${key.key}", plugin, player) {
            if (entityId == interaction.entityId) {
                callback(this)
            }
        }
         */

    }
}