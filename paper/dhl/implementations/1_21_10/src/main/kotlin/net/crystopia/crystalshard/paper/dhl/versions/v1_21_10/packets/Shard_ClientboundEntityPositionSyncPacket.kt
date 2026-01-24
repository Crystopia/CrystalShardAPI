package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundEntityPositionSyncPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundPlayerLookAtPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.commands.arguments.EntityAnchorArgument
import net.minecraft.network.protocol.game.ClientboundEntityPositionSyncPacket
import net.minecraft.network.protocol.game.ClientboundPlayerLookAtPacket
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import org.bukkit.craftbukkit.CraftWorld

class Shard_ClientboundEntityPositionSyncPacket : IPacket<ClientboundEntityPositionSyncPacketData> {

    override fun createPacket(
        packetObj: ClientboundEntityPositionSyncPacketData
    ): ClientboundEntityPositionSyncPacket {
        return ClientboundEntityPositionSyncPacket()
    }
}