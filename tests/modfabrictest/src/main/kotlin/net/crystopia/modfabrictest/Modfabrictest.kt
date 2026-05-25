package net.crystopia.modfabrictest

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTabListPacketData
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.minecraft.network.chat.Component
import org.slf4j.LoggerFactory

object Modfabrictest : ModInitializer {
    private val logger = LoggerFactory.getLogger("modfabrictest")

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Hello Fabric world!")

        ServerPlayerEvents.JOIN.register { player ->
            println("Joined ${player.name}")
            val packet = PacketBuilder.setTabList(
                ClientboundTabListPacketData(
                    Component.literal("EIER"),
                    Component.literal("EIER")
                )
            )
            println(packet)
            player.connection.send(packet)
        }
    }
}