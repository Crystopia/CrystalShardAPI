package net.crystopia.crystalshard.paper.core.extension

import net.crystopia.crystalshard.paper.core.experimental.ClientMods
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

fun Player.sendActionBar(plugin: JavaPlugin, text: Component, delaySeconds: Long, timeSeconds: Long) {
    Bukkit.getScheduler().runTaskTimer(plugin, Runnable {
        sendActionBar(text)
    }, delaySeconds, timeSeconds * 20L)
}

fun Player.transfer(ip: String, port: Int, message: Component?) {
    if (message != null) {
        sendMessage(message)
    }
    transfer(ip, port)
}

fun Player.sendBossBar(text: Component, process: Float, color: BossBar.Color, overlay: BossBar.Overlay) {
    showBossBar(BossBar.bossBar(text, process, color, overlay))
}

fun Player.sendBossBar(
    plugin: JavaPlugin,
    text: Component,
    process: Float,
    actionBarTaskProcess: Float,
    color: BossBar.Color,
    overlay: BossBar.Overlay,
    delaySeconds: Long,
    timeSeconds: Long,
) {
    Bukkit.getScheduler().runTaskTimer(plugin, Runnable {
        showBossBar(BossBar.bossBar(text, process * actionBarTaskProcess, color, overlay))
    }, delaySeconds, timeSeconds * 20L)
}

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

fun Player.removeServerPacketListener(key: String) {
    val serverPlayer = (player as CraftPlayer).handle
    val channel = serverPlayer.connection.connection.channel

    channel.pipeline().remove(key)
}