package net.crystopia.crystalshard.paper.panic.extension

import net.crystopia.crystalshard.paper.panic.experimental.ClientMods
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * **EXPERIMENTAL**
 *
 * Checks whether the player has installed a specific mod.
 * Use of translatable components...
 *
 * Note: You need to handle the checked players yourself.
 *
 * ```
 * val checked = mutableListOf<UUID>()
 *
 * @EventHandler
 * fun onMove(event: PlayerMoveEvent) {
 *     if (!checked.contains(event.player.uniqueId)) {
 *         checked.add(event.player.uniqueId)
 *         event.player.clientMods(Main.instance) {
 *             onMod("text.skinlayers.title") {
 *                 check { hasMod ->
 *                     if (hasMod) {
 *                         disconnect()
 *                     }
 *                 }
 *             }
 *         }
 *     }
 * }
 * ```
 */
fun Player.clientMods(plugin: JavaPlugin, callback: ClientMods.() -> Unit): Player {
    val clientMods = ClientMods(this, plugin)
    callback(
        clientMods
    )
    return this
}