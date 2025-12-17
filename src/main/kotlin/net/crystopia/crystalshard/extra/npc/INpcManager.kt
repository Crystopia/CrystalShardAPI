package net.crystopia.crystalshard.extra.npc

import org.bukkit.NamespacedKey
import org.ktorm.entity.Entity

interface INpcManager {
    var registeredNpcs: MutableMap<NamespacedKey, INpc>
    fun register(key: NamespacedKey, npcObj: INpc)
    fun unRegister(key: NamespacedKey): Boolean
    fun getNpc(key: NamespacedKey): INpc?
    fun getNpcByEntityId(entityId: Int): INpc?
}