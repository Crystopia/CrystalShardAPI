package net.crystopia.crystalshard.extras.npc.default

import net.crystopia.crystalshard.shared.interfaces.npcs.INpc
import net.crystopia.crystalshard.common.utils.Log
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 * Util class for easily adding the function to spawn the NPC for the player upon joining.
 */

class NpcEvents(var npcs: MutableList<INpc>, var debug: Boolean = false) : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {

        npcs.forEach { npc ->
            if (npc.spawnOnJoin!!) {
                if (debug) Log.debug("Added NPC tp Player on join (${npc.id.namespace}:${npc.id.key})")
                npc.spawnAll()
            }
        }
    }
}