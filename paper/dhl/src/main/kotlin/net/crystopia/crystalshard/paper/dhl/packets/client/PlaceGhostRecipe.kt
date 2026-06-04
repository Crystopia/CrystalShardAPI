package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.builder.RecipeEntryBuilder
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlaceGhostRecipePacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.PAPER_1_21_1
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_10.data.packets.PAPER_1_21_10
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_11.data.packets.PAPER_1_21_11
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_9.data.packets.PAPER_1_21_9
import net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil

fun ClientPacketFactory.placeGhostRecipe(
    containerId: Int,
    recipe: RecipeEntry,
    callback: (packet: ClientPacket<ClientboundPlaceGhostRecipePacketData>) -> Unit
): ClientPacket<ClientboundPlaceGhostRecipePacketData> {

    val shardPacket = ClientPacket<ClientboundPlaceGhostRecipePacketData>()
    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            val entry = RecipeEntryBuilder.PAPER_1_21_11(
                recipe
            )
            val data = ClientboundPlaceGhostRecipePacketData(
                containerId = containerId,
                recipeDisplay = entry.recipeDisplay!!,
                recipe = entry.recipe!!
            )
            shardPacket.packetData = data
            ClientPacketBuilder.placeGhostRecipe(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            val entry = RecipeEntryBuilder.PAPER_1_21_10(
                recipe
            )
            val data = ClientboundPlaceGhostRecipePacketData(
                containerId = containerId,
                recipeDisplay = entry.recipeDisplay!!,
                recipe = entry.recipe!!
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.placeGhostRecipe(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            val entry = RecipeEntryBuilder.PAPER_1_21_9(
                recipe
            )
            val data = ClientboundPlaceGhostRecipePacketData(
                containerId = containerId,
                recipeDisplay = entry.recipeDisplay!!,
                recipe = entry.recipe!!
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.placeGhostRecipe(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            val entry = RecipeEntryBuilder.PAPER_1_21_1(
                recipe
            )
            val data = ClientboundPlaceGhostRecipePacketData(
                containerId = containerId,
                recipeDisplay = entry.recipeDisplay!!,
                recipe = entry.recipe!!
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.placeGhostRecipe(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}