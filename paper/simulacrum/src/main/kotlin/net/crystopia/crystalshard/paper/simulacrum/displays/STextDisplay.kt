package net.crystopia.crystalshard.paper.simulacrum.displays

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.packets.client.setEntityData
import net.crystopia.crystalshard.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityDataSerializerType
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.simulacrum.displays.data.CustomTextDisplayData
import net.crystopia.crystalshard.paper.simulacrum.types.interfaces.displays.IDisplay
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.minecraft.network.chat.Component
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.entity.TextDisplay

class STextDisplay(
    override var id: NamespacedKey,
    override var type: org.bukkit.entity.EntityType,
    override var entity: TextDisplay,
) : IDisplay<TextDisplay>, DisplayInteraction<TextDisplay>(entity) {

    var data = CustomTextDisplayData()

    fun text(component: net.kyori.adventure.text.Component, players: MutableList<Player>) {

        ClientPacketFactory.setEntityData(
            entity, mutableListOf(
                EntityMetadata(
                    index = 23,
                    type = EntityDataSerializerType.COMPONENT,
                    value = component
                )
            )
        ) { packet ->
            packet.send(players)
        }


        data.text = component
    }


}