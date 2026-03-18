package net.crystopia.crystalshard.paper.panic.experimental

import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.ServerPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.removeServerPacketListener
import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.BlockEntityType
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.block.BlockType
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class ClientMods(val player: Player, val plugin: JavaPlugin) {
    val listenerKey = "${this.player.uniqueId}_moddetect"
    private val blockPos = BlockPos(
        player.location.x.toInt(), player.location.y.toInt(), player.location.z.toInt()
    )

    class Mod(
        var player: Player, val plugin: JavaPlugin, val listenerKey: String
    ) {
        private var component: Component? = null

        fun message(message: Component): Mod {
            this.component = message
            return this
        }

        fun check(callback: Mod.(hasMod: Boolean) -> Unit): Mod {
            ServerPacketFactory.signUpdateEvent(
                Shard_ServerPacketData(
                    player = player,
                    name = NamespacedKey("moddetect", listenerKey),
                    shouldPublish = false,
                    plugin = plugin
                )
            ) {
                if (lines[0] != "NONE") {
                    callback(true)
                } else callback(false)
                player.removeServerPacketListener(NamespacedKey("moddetect", listenerKey).toString())
            }
            return this
        }

        fun disconnect() {
            player.kick(
                component ?: Component.text().text("<color:#ff6b66>You are using blocked mods.</color>").build()
            )
        }
    }

    fun onMod(translationKey: String, callback: Mod.() -> Unit): ClientMods {
        detectPacketsSender(translationKey)
        val mod = Mod(player, plugin, listenerKey)
        callback(mod)
        return this
    }

    private fun detectPacketsSender(key: String) {

        ClientPacketFactory.createBlockUpdatePacket(
            blockPos, BlockType.OAK_SIGN
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        ClientPacketFactory.createBlockEntityDataPacket(
            blockPos, BlockEntityType.SIGN, buildNBT(key)
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        ClientPacketFactory.createOpenSignEditorPacket(
            blockPos, true
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        player.closeInventory()

        ClientPacketFactory.createBlockUpdatePacket(
            blockPos, BlockType.AIR
        ) { packet ->
            packet.send(mutableListOf(player))
        }
    }

    private fun buildNBT(key: String): String {
        return """
          {front_text:{messages:[{"translate":"$key","fallback":"NONE"},"","",""]}}
      """.trimIndent()
    }
}