package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.builder.RecipeEntryBuilder
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipeBookAddPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.PAPER_1_21_1
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_10.PAPER_1_21_10
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_11.PAPER_1_21_11
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_9.PAPER_1_21_9
import net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil

fun ClientPacketFactory.addRecipeBook(
    recipes: MutableList<RecipeEntry>,
    replace: Boolean,
    callback: (packet: ClientPacket<ClientboundRecipeBookAddPacketData>) -> Unit
): ClientPacket<ClientboundRecipeBookAddPacketData> {

    val shardPacket = ClientPacket<ClientboundRecipeBookAddPacketData>()
    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            val data = ClientboundRecipeBookAddPacketData(
                recipeEntries = recipes.map {
                    RecipeEntryBuilder.PAPER_1_21_11(
                        it,
                    )
                },
                replace = replace
            )
            shardPacket.packetData = data
            ClientPacketBuilder.addRecipeBook(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            val data = ClientboundRecipeBookAddPacketData(
                recipeEntries = recipes.map {
                    RecipeEntryBuilder.PAPER_1_21_10(
                        it,
                    )
                },
                replace = replace
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.addRecipeBook(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            val data = ClientboundRecipeBookAddPacketData(
                recipeEntries = recipes.map {
                    RecipeEntryBuilder.PAPER_1_21_9(
                        it,
                    )
                },
                replace = replace
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.addRecipeBook(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            val data = ClientboundRecipeBookAddPacketData(
                recipeEntries = recipes.map {
                    RecipeEntryBuilder.PAPER_1_21_1(
                        it,
                    )
                },
                replace = replace
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.addRecipeBook(
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