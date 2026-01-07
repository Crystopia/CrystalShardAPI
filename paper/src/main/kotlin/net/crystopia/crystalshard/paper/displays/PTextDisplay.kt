package net.crystopia.crystalshard.extras.displays

import net.crystopia.crystalshard.extras.displays.data.CustomTextDisplayData
import net.crystopia.crystalshard.extras.factories.PacketFactory
import net.crystopia.crystalshard.shared.interfaces.displays.IDisplay
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.minecraft.network.chat.Component
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player

class PTextDisplay(
    override var id: NamespacedKey,
    override var type: EntityType<*>,
    override var entity: Display,
) : IDisplay, DisplayInteraction(entity) {

    var data = CustomTextDisplayData()

    fun text(component: net.kyori.adventure.text.Component, players: MutableList<Player>) {


        val accessor = EntityDataAccessor(23, EntityDataSerializers.COMPONENT)
        PacketFactory.setEntityDataPacket(
            entity.id, mutableListOf(
                SynchedEntityData.DataValue.create(
                    accessor, Component.literal(
                        LegacyComponentSerializer.legacySection().serialize(component)
                    )
                )
            )
        ) { packet ->
            PacketFactory.sendPacket(packet, players)
        }
        data.text = component
    }


}