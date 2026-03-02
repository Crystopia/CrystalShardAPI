package net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.client


import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.kyori.adventure.text.Component
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.player.Player
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.craftbukkit.CraftParticle
import org.bukkit.craftbukkit.block.CraftBlockType
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

class Shard_ClientboundSetEntityDataPacket : IPacket<ClientboundSetEntityDataPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetEntityDataPacketData
    ): ClientboundSetEntityDataPacket {
        val list = mutableListOf<SynchedEntityData.DataValue<*>>()
        packetObj.entityData.forEach { (index, type, value) ->
            list.add(
                buildMetaData(
                    EntityMetadata(
                        index = index,
                        type = type,
                        value = value
                    )
                )
            )
        }
        return ClientboundSetEntityDataPacket(packetObj.entityId, list)
    }

    @Suppress("UNCHECKED_CAST")
    fun buildMetaData(data: EntityMetadata<*>): SynchedEntityData.DataValue<*> {
        when (data.type) {
            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.DATA_PLAYER_MODE_CUSTOMISATION -> {

                val accessor = Player.DATA_PLAYER_MODE_CUSTOMISATION
                return SynchedEntityData.DataValue.create(accessor, (data.value as Byte))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.BYTE -> {

                val serializer = EntityDataSerializers.BYTE
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, (data.value as Byte))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.INT -> {

                val serializer = EntityDataSerializers.INT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, (data.value as Int))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.LONG -> {

                val serializer = EntityDataSerializers.LONG
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, (data.value as Long))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.FLOAT -> {

                val serializer = EntityDataSerializers.FLOAT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, (data.value as Float))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.STRING -> {

                val serializer = EntityDataSerializers.STRING
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, (data.value as String))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.COMPONENT, EntityDataSerializerType.OPTIONAL_COMPONENT -> {

                val serializer = EntityDataSerializers.COMPONENT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, PaperAdventure.asVanilla(data.value as Component))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.ITEM_STACK -> {

                val serializer = EntityDataSerializers.ITEM_STACK
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, CraftItemStack.asNMSCopy(data.value as ItemStack))
            }

            EntityDataSerializerType.BLOCK_STATE, EntityDataSerializerType.OPTIONAL_BLOCK_STATE -> {

                val serializer = EntityDataSerializers.BLOCK_STATE
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    CraftBlockType.bukkitToMinecraft(data.value as Material).defaultBlockState()
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.BOOLEAN -> {
                val serializer = EntityDataSerializers.BOOLEAN
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, data.value as Boolean)
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.PARTICLE -> {
                val serializer = EntityDataSerializers.PARTICLE
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    CraftParticle.createParticleParam((data.value as Particle), data.value as Particle)
                )
            }
            // TODO: PARTICLES
            else -> {
                TODO()
            }
        }
    }
}