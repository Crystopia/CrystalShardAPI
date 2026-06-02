package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundCooldownPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import org.bukkit.Material
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

fun ClientPacketFactory.applyCooldown(
    item: Material, duration: Int, callback: (packet: ClientPacket<ClientboundCooldownPacketData>) -> Unit
): ClientPacket<ClientboundCooldownPacketData> {

    val data = ClientboundCooldownPacketData(
        CraftItemStack.asNMSCopy(ItemStack(item)), duration
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.applyCooldown(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.applyCooldown(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.applyCooldown(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.applyCooldown(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundCooldownPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}