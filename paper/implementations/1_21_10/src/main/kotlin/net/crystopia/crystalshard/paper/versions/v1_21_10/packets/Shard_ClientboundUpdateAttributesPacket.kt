package net.crystopia.crystalshard.paper.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundUpdateAttributesPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket

class Shard_ClientboundUpdateAttributesPacket : IPacket<ClientboundUpdateAttributesPacketData> {
    override fun createPacket(packetObj: ClientboundUpdateAttributesPacketData): ClientboundUpdateAttributesPacket {

        TODO()

        /*
         val attributes: MutableList<AttributeInstance> = mutableListOf()

        packetObj.attributes.forEach { attribute ->

            val modifiers = attribute.modifiers.map { (id, amount, operation) ->
                AttributeModifier(ResourceLocation.read(id).orThrow, amount, operation)
            }

            attributes.add(
                AttributeInstance(
                        attribute.id,
                    Attribute
                    )
            )
        }

        return ClientboundUpdateAttributesPacket(
            packetObj.entityId,
            attributes
        )
         */
    }
}