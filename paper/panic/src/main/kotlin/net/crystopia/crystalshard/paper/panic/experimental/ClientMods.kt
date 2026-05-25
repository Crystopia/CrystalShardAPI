package net.crystopia.crystalshard.paper.panic.experimental

import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.ServerPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.dhl.shared.enums.entities.BlockEntityType
import net.crystopia.crystalshard.paper.dhl.extension.removeServerPacketListener
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.createBlockEntityData
import net.crystopia.crystalshard.paper.dhl.packets.client.createBlockUpdate
import net.crystopia.crystalshard.paper.dhl.packets.client.createOpenSignEditor
import net.crystopia.crystalshard.paper.dhl.packets.server.signUpdateEvent
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player

class ClientMods(val player: Player) {
    val listenerKey = "${this.player.uniqueId.toString().replace("-", "")}_moddetect"
    private val blockPos = BlockPos(
        player.location.x.toInt(), player.location.y.toInt(), player.location.z.toInt()
    )

    class Mod(
        var player: Player, val listenerKey: String
    ) {
        private var component: Component? = null

        fun message(message: Component): Mod {
            this.component = message
            return this
        }

        fun check(callback: Mod.(hasMod: Boolean) -> Unit): Mod {
            ServerPacketFactory.signUpdateEvent(
                player,
                NamespacedKey("moddetect", listenerKey),
                false
            ) {
                println(lines)
                if (lines[0] != "NONE") {
                    callback(true)
                } else callback(false)

            }
            return this
        }

        fun disconnect() {
            player.connection.disconnect(
                component ?: Component.text().text("<color:#ff6b66>You are using blocked mods.</color>").build()
            )
        }
    }

    fun onMod(translationKey: String, callback: Mod.() -> Unit): ClientMods {
        detectPacketsSender(translationKey)
        val mod = Mod(player, listenerKey)
        callback(mod)
        return this
    }

    private fun detectPacketsSender(key: String) {

        ClientPacketFactory.createBlockUpdate(
            blockPos, Material.OAK_SIGN
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        ClientPacketFactory.createBlockEntityData(
            blockPos, BlockEntityType.SIGN, buildNBT(key)
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        ClientPacketFactory.createOpenSignEditor(
            blockPos, true
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        player.closeInventory()

        ClientPacketFactory.createBlockUpdate(
            blockPos, Material.AIR
        ) { packet ->
            packet.send(mutableListOf(player))
        }
    }

    private fun buildNBT(key: String): String {
        return """
          {front_text:{messages:[{"translate":"$key","fallback":"NONE"},{"translate":"$key","fallback":"NONE"},{"translate":"$key","fallback":"NONE"},{"translate":"$key","fallback":"NONE"}]}}
      """.trimIndent()
    }
}