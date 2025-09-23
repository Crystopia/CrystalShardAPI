package net.crystopia.crystalshard.extension

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
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