package net.crystopia.crystalshard.entities

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

class CrystalPlayer(val player: Player) {
    
    fun transfer(ip: String, port: Int, message: Component?) {
        if (message != null) {
            player.sendMessage(message)
        }
        player.transfer(ip, port)
    }

    fun sendBossBar(text: Component, process: Float, color: BossBar.Color, overlay: BossBar.Overlay) {
        player.showBossBar(BossBar.bossBar(text, process, color, overlay))
    }
}