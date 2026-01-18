package net.crystopia.crystalshard.paper.simulacrum.displays

import net.crystopia.crystalshard.paper.dhl.PacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityDataSerializerType
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityMetadata
import net.crystopia.crystalshard.paper.simulacrum.displays.data.CustomTextDisplayData
import net.crystopia.crystalshard.paper.simulacrum.types.interfaces.displays.IDisplay
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.minecraft.network.chat.Component
import net.minecraft.network.syncher.EntityDataSerializers
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.entity.TextDisplay

class PTextDisplay(
    override var id: NamespacedKey,
    override var type: net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityType,
    override var entity: TextDisplay,
) : IDisplay<TextDisplay>, DisplayInteraction<TextDisplay>(entity) {

    var data = CustomTextDisplayData()

    fun text(component: net.kyori.adventure.text.Component, players: MutableList<Player>) {


        PacketFactory.setEntityDataPacket(
            entity.entityId, mutableListOf(
                EntityMetadata(
                    index = 23,
                    type = EntityDataSerializerType.COMPONENT,
                    value = Component.literal(
                        LegacyComponentSerializer.legacySection().serialize(component)
                    )
                )
            )
        ) { packet ->
            packet.send(players)
        }


        data.text = component
    }


}