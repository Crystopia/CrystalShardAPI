package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipeBookAddPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.enums.recipes.RecipeBookCategories
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder
import net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry
import net.crystopia.crystalshard.paper.dhl.types.recipes.display
import net.crystopia.crystalshard.paper.dhl.types.recipes.ingredients
import net.crystopia.crystalshard.paper.dhl.types.recipes.recipe
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.minecraft.world.item.crafting.ShapedRecipePattern
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry
import net.minecraft.world.item.crafting.display.RecipeDisplayId
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.craftbukkit.inventory.CraftRecipe
import org.bukkit.craftbukkit.inventory.CraftShapedRecipe
import org.bukkit.inventory.ShapedRecipe
import java.util.*
import kotlin.experimental.or

fun ClientPacketFactory.addRecipeBook(
    recipes: MutableList<RecipeEntry>,
    replace: Boolean,
    callback: (packet: Shard_Packet<ClientboundRecipeBookAddPacketData>) -> Unit
): Shard_Packet<ClientboundRecipeBookAddPacketData> {

    val data = ClientboundRecipeBookAddPacketData(
        recipeDisplayEntries = recipes.map { choice ->
            net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeEntry(
                flags = (choice.highlight or choice.showNotification), // TODO: TEST THIS!!
                recipeDisplay = RecipeDisplayEntry(
                    RecipeDisplayId(choice.order),
                    choice.display(),
                    OptionalInt.of(choice.group),
                    RecipeBookCategories.convert(
                        choice.category
                    ).category,
                    Optional.ofNullable(
                        choice.ingredients()
                    )
                ),
                id = NamespacedKey(choice.id.namespace, choice.id.key),
                recipe = choice.recipe()
            )
        }.toMutableList(),
        replace = replace,
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.addRecipeBook(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.PacketBuilder.addRecipeBook(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.PacketBuilder.addRecipeBook(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.addRecipeBook(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundRecipeBookAddPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}