package net.crystopia.crystalshard.paper.versions.v1_21_1.packets

import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundUpdateAttributesPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import java.util.*

class Shard_ClientboundUpdateAttributesPacket : IPacket<ClientboundUpdateAttributesPacketData> {
    override fun createPacket(packetObj: ClientboundUpdateAttributesPacketData): ClientboundUpdateAttributesPacket {
        TODO("")
        /*

        return ClassUtil.initFakeInstance(
            ClientboundTeleportEntityPacket::class.java
        ) {
            setField("id", packetObj.entityId)
            setField("x", packetObj.location.x)
            setField("y", packetObj.location.y)
            setField("z", packetObj.location.z)
            setField("yRot", packetObj.location.yaw.toInt().toByte())
            setField("xRot", packetObj.location.pitch.toInt().toByte())
            setField("onGround", false)
        } as ClientboundTeleportEntityPacket

        val attributes: MutableList<ClientboundUpdateAttributesPacket.AttributeSnapshot> = mutableListOf()

        packetObj.attributes.forEach { attribute ->

            val modifiers = attribute.modifiers.map { (id, amount, operation) ->
                AttributeModifier(UUID.randomUUID(), id, amount, operation)
            }

            attributes.add(
                ClientboundUpdateAttributesPacket.AttributeSnapshot(
                    attribute.id,
                    attribute.value,
                    modifiers
                )
            )
        }

        REFlection

        return ClientboundUpdateAttributesPacket(
            packetObj.entityId,
            attributes.
        )
         */
    }
}