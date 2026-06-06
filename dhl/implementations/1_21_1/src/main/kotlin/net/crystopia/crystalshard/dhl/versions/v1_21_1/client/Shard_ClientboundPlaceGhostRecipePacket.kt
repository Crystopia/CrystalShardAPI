package net.crystopia.crystalshard.dhl.versions.v1_21_1.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlaceGhostRecipePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundPlaceGhostRecipePacket
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.RecipeHolder

class Shard_ClientboundPlaceGhostRecipePacket : IClientPacket<ClientboundPlaceGhostRecipePacketData> {

    override fun createPacket(
        packetObj: ClientboundPlaceGhostRecipePacketData
    ): ClientboundPlaceGhostRecipePacket {
        val holder = RecipeHolder(
            ResourceLocation.tryBuild(packetObj.id.namespace, packetObj.id.key)!!,
            packetObj.recipe as net.minecraft.world.item.crafting.Recipe<*>
        )

        return ClientboundPlaceGhostRecipePacket(
            packetObj.containerId, holder
        )
    }
}