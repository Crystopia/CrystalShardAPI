package net.crystopia.crystalshard.entities

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player


/**
 *
 * Extended Player from Bukkit to be more flexible.
 *
 */
class CrystalPlayer(val handle: Player) {
    
    fun transfer(ip: String, port: Int, message: Component?) {
        if (message != null) {
            handle.sendMessage(message)
        }
        handle.transfer(ip, port)
    }

    fun sendBossBar(text: Component, process: Float, color: BossBar.Color, overlay: BossBar.Overlay) {
        handle.showBossBar(BossBar.bossBar(text, process, color, overlay))
    }
}