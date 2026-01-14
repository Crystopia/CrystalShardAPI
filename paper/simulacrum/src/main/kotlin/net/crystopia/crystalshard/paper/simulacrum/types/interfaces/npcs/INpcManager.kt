package net.crystopia.crystalshard.paper.simulacrum.types.interfaces.npcs

import org.bukkit.NamespacedKey

/**
 * General Interface for a very simple NPC Manager. 
 * Implementation for it in: `net.crystopia.crystalshard.paper.npc.NpcManager`
 * @see NpcManager
 */
interface INpcManager {
    var registeredNpcs: MutableMap<NamespacedKey, INpc>
    fun register(key: NamespacedKey, npcObj: INpc)
    fun unRegister(key: NamespacedKey): Boolean
    fun getNpc(key: NamespacedKey): INpc?
    fun getNpcByEntityId(entityId: Int): INpc?
}