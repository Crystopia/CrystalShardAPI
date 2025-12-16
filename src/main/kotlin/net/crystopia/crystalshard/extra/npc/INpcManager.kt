package net.crystopia.crystalshard.extra.npc

import org.bukkit.NamespacedKey

interface INpcManager {

    var registeredNpcs: MutableMap<NamespacedKey, net.minecraft.world.entity.npc.Npc>

    fun register(
        key: NamespacedKey, npcObj: net.minecraft.world.entity.npc.Npc
    )

    fun unRegister(
        key: NamespacedKey
    )

    fun getNpc(key: NamespacedKey): Npc?

}