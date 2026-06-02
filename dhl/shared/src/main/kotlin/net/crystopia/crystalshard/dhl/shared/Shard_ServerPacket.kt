package net.crystopia.crystalshard.dhl.shared

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.minecraft.world.entity.player.Player

data class Shard_ServerPacket(
    var player: Player,
    var name: NamespacedKey,
    var shouldPublish: Boolean
)