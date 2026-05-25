package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementProgress

data class ClientboundUpdateAdvancementsPacketData(
    var reset: Boolean,
    var added: MutableMap<NamespacedKey, Advancement>,
    var removed: MutableSet<NamespacedKey>,
    var progress: MutableMap<NamespacedKey, AdvancementProgress>,
    var showAdvancements: Boolean,
)
