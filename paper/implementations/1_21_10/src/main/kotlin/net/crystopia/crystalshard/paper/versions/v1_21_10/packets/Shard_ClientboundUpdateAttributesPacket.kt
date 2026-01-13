package net.crystopia.crystalshard.paper.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundUpdateAttributesPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.ai.attributes.AttributeInstance
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import java.util.*
import java.util.function.Consumer

class Shard_ClientboundUpdateAttributesPacket : IPacket<ClientboundUpdateAttributesPacketData> {
    override fun createPacket(packetObj: ClientboundUpdateAttributesPacketData): ClientboundUpdateAttributesPacket {

        val attributes: MutableList<AttributeInstance> = mutableListOf()

        packetObj.attributes.forEach { attribute ->
            val modifiers = attribute.modifiers.map { (id, amount, operation) ->
                AttributeModifier(ResourceLocation.read(id).orThrow, amount, operation)
            }

            val instance =  AttributeInstance(
                attribute.id,
                Consumer { instance -> }
            )
            instance.baseValue = attribute.value
            instance.modifiers.addAll(modifiers)
            attributes.add(instance)
        }

        return ClientboundUpdateAttributesPacket(
            packetObj.entityId,
            attributes
        )
    }
}