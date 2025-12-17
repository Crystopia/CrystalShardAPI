package net.crystopia.crystalshard.extra.npc

import org.bukkit.NamespacedKey

object NpcManager : INpcManager{
    override var registeredNpcs: MutableMap<NamespacedKey, INpc> = mutableMapOf()

    override fun register(key: NamespacedKey, npcObj: INpc) {
        registeredNpcs[key] = npcObj
    }

    override fun unRegister(key: NamespacedKey): Boolean {
        try {
            registeredNpcs.remove(key)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun getNpc(key: NamespacedKey): INpc? {
        return registeredNpcs[key]
    }

    override fun getNpcByEntityId(entityId: Int): INpc? {
        registeredNpcs.filter { (key, value) ->
            return if (value.entityId == entityId) {
                value
            } else null
        }
        return null
    }

}