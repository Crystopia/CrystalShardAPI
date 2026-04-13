package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundDamageEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundDamageEventPacket
import org.bukkit.craftbukkit.damage.CraftDamageSource
import org.bukkit.craftbukkit.entity.CraftEntity

class Shard_ClientboundDamageEventPacket : IPacket<ClientboundDamageEventPacketData> {

    override fun createPacket(
        packetObj: ClientboundDamageEventPacketData
    ): ClientboundDamageEventPacket {
        return ClientboundDamageEventPacket(
            (packetObj.entity as CraftEntity).handle, (packetObj.damageSource as CraftDamageSource).handle
        )
    }
}