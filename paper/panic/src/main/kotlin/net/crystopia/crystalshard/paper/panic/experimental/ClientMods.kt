package net.crystopia.crystalshard.paper.panic.experimental

import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.ServerPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.removeServerPacketListener
import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.block.BlockType
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class ClientMods(val player: Player, val plugin: JavaPlugin) {
    val listenerKey = UUID.fromString("173c9e65-66a5-44c7-b21d-b307bdb06423").toString()
    private val blockPos = BlockPos(
        player.location.x.toInt(), player.location.y.toInt(), player.location.z.toInt()
    )

    class Mod(
        var player: Player, val plugin: JavaPlugin, val blockPos: BlockPos, val listenerKey: String
    ) {
        private var component: Component? = null

        fun message(message: Component): Mod {
            this.component = message
            return this
        }

        fun check(callback: Mod.(hasMod: Boolean) -> Unit): Mod {
            ServerPacketFactory.signUpdateEvent(
                Shard_ServerPacketData(
                    player = player, name = NamespacedKey(listenerKey,listenerKey), plugin = plugin
                )
            ) {
                player.removeServerPacketListener(listenerKey)

                if (lines[0] != "NONE") {
                    callback(true)
                }
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
        val mod = Mod(player, plugin, blockPos, listenerKey)
        callback(mod)
        return this
    }

    fun detectPacketsSender(key: String) {

        ClientPacketFactory.createBlockUpdatePacket(
            blockPos, BlockType.OAK_SIGN
        ) { packet ->
            packet.send(mutableListOf(player))
        }


        ClientPacketFactory.createBlockEntityDataPacket(
            blockPos, BlockType.OAK_SIGN, buildNBT(key)
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        ClientPacketFactory.createOpenSignEditorPacket(
            blockPos, true
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        ClientPacketFactory.createBlockUpdatePacket(
            blockPos, BlockType.AIR
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        player.closeInventory()
    }

    private fun buildNBT(key: String): MutableMap<String, Any> {

        val map = mutableMapOf<String, Any>()
        map["id"] = "minecraft:sign"

        val frontText = mutableMapOf<String, Any>()
        val messages = mutableListOf<MutableMap<String, Any>>()

        repeat(4) {
            val line = mutableMapOf<String, Any>()
            line["translate"] = key
            line["fallback"] = "NONE"
            messages.add(line)
        }

        frontText["messages"] = messages
        frontText["color"] = "black"
        frontText["has_glowing_text"] = false

        map["front_text"] = frontText
        return map
    }
}