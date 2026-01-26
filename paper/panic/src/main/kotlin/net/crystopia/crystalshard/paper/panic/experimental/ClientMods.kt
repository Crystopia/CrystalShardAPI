package net.crystopia.crystalshard.paper.panic.experimental

import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.dhl.PacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.removeServerPacketListener
import net.crystopia.crystalshard.paper.dhl.server.ServerboundSignUpdatePacketUtil
import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.enums.blocks.BlockEntityType
import net.kyori.adventure.text.Component
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import org.bukkit.block.BlockType
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class ClientMods(val player: Player, val plugin: JavaPlugin) {
    val listenerKey = UUID.fromString("173c9e65-66a5-44c7-b21d-b307bdb06423").toString()
    private val blockPos = BlockPos(
        player.location.x.toInt(),
        player.location.y.toInt(),
        player.location.z.toInt()
    )

    class Mod(
        var player: Player,
        val plugin: JavaPlugin,
        val blockPos: BlockPos,
        val listenerKey: String
    ) {
        private var component: Component? = null

        fun message(message: Component): Mod {
            this.component = message
            return this
        }

        fun check(callback: Mod.(hasMod: Boolean) -> Unit): Mod {
            ServerboundSignUpdatePacketUtil.attach(
                listenerKey,
                plugin,
                player
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

        PacketFactory.createBlockUpdatePacket(
            blockPos,
            BlockType.OAK_SIGN
        ) { packet ->
            packet.send(mutableListOf(player))
        }


        PacketFactory.createBlockEntityDataPacket(
            blockPos,
            BlockEntityType.SIGN,
            buildNBT(key)
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        PacketFactory.createOpenSignEditorPacket(
            blockPos,
            true
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        PacketFactory.createBlockUpdatePacket(
            blockPos,
            BlockType.AIR
        ) { packet ->
            packet.send(mutableListOf(player))
        }

        player.closeInventory()
    }

    private fun buildNBT(key: String): CompoundTag {
        val nbt = CompoundTag()
        nbt.putString("id", "minecraft:sign")

        val frontText = CompoundTag()
        val messages = ListTag()

        repeat(4) {
            val line = CompoundTag()
            line.putString(
                "translate",
                key
            )
            line.putString(
                "fallback",
                "NONE"
            )
            messages.add(line)
        }

        frontText.put("messages", messages)
        frontText.putString("color", "black")
        frontText.putBoolean("has_glowing_text", false)

        nbt.put("front_text", frontText)
        return nbt
    }
}