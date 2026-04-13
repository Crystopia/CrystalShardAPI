package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import org.bukkit.NamespacedKey
import org.bukkit.advancement.Advancement
import org.bukkit.advancement.AdvancementProgress

data class ClientboundUpdateAdvancementsPacketData(
    var reset: Boolean,
    var added: MutableList<Advancement>,
    var removed: MutableSet<NamespacedKey>,
    var progress: MutableMap<NamespacedKey, AdvancementProgress>,
    var showAdvancements: Boolean,
)
