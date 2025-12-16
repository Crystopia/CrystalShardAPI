package net.crystopia.crystalshard.extra.npc

import net.minecraft.world.entity.npc.Npc
import org.bukkit.NamespacedKey

object NpcManager : INpcManager{
    override var registeredNpcs: MutableMap<NamespacedKey, Npc> = mutableMapOf()

    override fun register(key: NamespacedKey, npcObj: Npc) {
        TODO("Not yet implemented")
    }

    override fun unRegister(key: NamespacedKey) {
        TODO("Not yet implemented")
    }

    override fun getNpc(key: NamespacedKey): net.crystopia.crystalshard.extra.npc.Npc? {
        TODO("Not yet implemented")
    }


}