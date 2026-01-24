package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundPlayerLookAtPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.commands.arguments.EntityAnchorArgument
import net.minecraft.network.protocol.game.ClientboundPlayerLookAtPacket
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import org.bukkit.craftbukkit.CraftWorld

class Shard_ClientboundPlayerLookAtPacket : IPacket<ClientboundPlayerLookAtPacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerLookAtPacketData
    ): ClientboundPlayerLookAtPacket {

        var fakeEntity = Display.ItemDisplay(
            EntityType.ITEM_DISPLAY,
            (packetObj.world as CraftWorld).handle
        )
        fakeEntity.id = packetObj.entityId
        fakeEntity.setPos(packetObj.x, packetObj.y, packetObj.z)

        val fromAnchor = EntityAnchorArgument.Anchor.valueOf(packetObj.fromAnchor.name)
        val toAnchor = EntityAnchorArgument.Anchor.valueOf(packetObj.toAnchor.name)

        return ClientboundPlayerLookAtPacket(
            fromAnchor, fakeEntity, toAnchor
        )
    }
}