package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundUpdateAttributesPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
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
            val instance =  AttributeInstance(
                attribute.id,
                Consumer { instance -> }
            )
            instance.baseValue = attribute.value
            // addOrUpdateTransientModifier    addTransientModifier    addOrReplacePermanentModifier
            attribute.modifiers.map { (id, amount, operation) ->
                instance.addPermanentModifier(AttributeModifier(id, amount, operation))
            }
            attributes.add(instance)
        }

        return ClientboundUpdateAttributesPacket(
            packetObj.entityId,
            attributes
        )
    }
}