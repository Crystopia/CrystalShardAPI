package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.attributes.Attribute
import net.crystopia.crystalshard.dhl.shared.data.attributes.AttributeModifiers
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundUpdateAttributesPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import org.bukkit.craftbukkit.attribute.CraftAttribute
import org.bukkit.craftbukkit.entity.CraftEntity
import org.bukkit.entity.Entity

/**
 * See more infos about status. [Entity_statuses](https://minecraft.wiki/w/Java_Edition_protocol/Entity_statuses)
 */
fun ClientPacketFactory.updateAttributes(
    entity: Entity,
    attributes: MutableList<net.crystopia.crystalshard.paper.dhl.types.attributes.Attribute>,
    callback: (packet: ClientPacket<ClientboundUpdateAttributesPacketData>) -> Unit
): ClientPacket<ClientboundUpdateAttributesPacketData> {


    val data = ClientboundUpdateAttributesPacketData(
        (entity as CraftEntity).handle, attributes.map { attr ->
            Attribute(
                id = CraftAttribute.bukkitToMinecraftHolder(attr.id as org.bukkit.attribute.Attribute),
                value = attr.value,
                modifiers = attr.modifiers.map {
                    AttributeModifiers(
                        id = it.id,
                        operation = AttributeModifier.Operation.valueOf(it.operation.name),
                        amount = it.amount,
                    )
                }.toMutableList()
            )
        }.toMutableList()
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.updateAttributesPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.updateAttributesPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.updateAttributesPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.updateAttributesPacket(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundUpdateAttributesPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}