package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipePacketData
import net.crystopia.crystalshard.dhl.shared.enums.recipes.RecipeState
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil

fun ClientPacketFactory.recipePacket(
    state: RecipeState,
    recipeIdsToChange: MutableList<org.bukkit.NamespacedKey>,
    recipeIdsToInit: MutableList<org.bukkit.NamespacedKey>,
    recipeBookSettings: net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeBookSettings,
    callback: (packet: Shard_Packet<ClientboundRecipePacketData>) -> Unit
): Shard_Packet<ClientboundRecipePacketData> {

    val shardPacket = Shard_Packet<ClientboundRecipePacketData>()

    val data = ClientboundRecipePacketData(
        state = state,
        recipeIdsToChange = recipeIdsToChange.map { NamespacedKey(it.namespace, it.key) }.toMutableList(),
        recipeIdsToInit = recipeIdsToInit.map { NamespacedKey(it.namespace, it.key) }.toMutableList(),
        recipeBookSettings = recipeBookSettings
    )
    shardPacket.packetData = data


    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11,
        ServerVersion.v1_21_9,
        ServerVersion.v1_21_10 -> {
            throw Exception("This is an 1.21.1 only Packet...")
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.recipePacket(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}