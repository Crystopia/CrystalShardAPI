package net.crystopia.crystalshard.dhl.shared.data.packets.server

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.minecraft.world.entity.player.Player

data class Shard_ServerPacketData(
    var player: Player,
    var name: NamespacedKey,
    var shouldPublish: Boolean
)