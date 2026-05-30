package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.world.entity.Entity

data class ClientboundTakeItemEntityPacketData(
    var itemId: Int,
    var player: Entity,
    var amount: Int
)
