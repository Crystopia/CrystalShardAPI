package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipeBookSettingsPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil

fun ClientPacketFactory.recipeBookSettings(
    recipeBookSettings: net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeBookSettings,
    callback: (packet: ClientPacket<ClientboundRecipeBookSettingsPacketData>) -> Unit
): ClientPacket<ClientboundRecipeBookSettingsPacketData> {

    val shardPacket = ClientPacket<ClientboundRecipeBookSettingsPacketData>()
    val data = ClientboundRecipeBookSettingsPacketData(
        recipeBookSettings = recipeBookSettings,
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.recipeBookSettings(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.recipeBookSettings(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.recipeBookSettings(
                data
            )
        }

        ServerVersion.v1_21_1 -> {

            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.recipeBookSettings(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}