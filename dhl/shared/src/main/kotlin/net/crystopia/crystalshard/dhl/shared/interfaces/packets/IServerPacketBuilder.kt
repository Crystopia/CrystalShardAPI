package net.crystopia.crystalshard.dhl.shared.interfaces.packets

import net.crystopia.crystalshard.dhl.shared.Shard_ServerPacket
import net.crystopia.crystalshard.dhl.shared.data.gui.Slot
import net.crystopia.crystalshard.dhl.shared.data.packets.server.*

interface IServerPacketBuilder {
    fun acceptTeleportationEvent(data: Shard_ServerPacket, event: (id: Int) -> Unit)
    fun containerButtonClickEvent(data: Shard_ServerPacket, event: (data: ButtonClickEvent) -> Unit)
    fun containerClickEvent(
        data: Shard_ServerPacket,
        items: MutableList<Slot>,
        event: (data: ContainerClickEvent) -> Unit
    )

    fun customClickActionEvent(data: Shard_ServerPacket, event: (data: CustomClickEvent) -> Unit)
    fun interactEvent(data: Shard_ServerPacket, event: (data: InteractEvent) -> Unit)
    fun movePlayerEvent(data: Shard_ServerPacket, event: (data: MovePlayerEvent) -> Unit)
    fun moveVehicleEvent(data: Shard_ServerPacket, event: (data: MoveVehicleEvent) -> Unit)
    fun playerActionEvent(data: Shard_ServerPacket, event: (data: PlayerActionEvent) -> Unit)
    fun playerCommandEvent(data: Shard_ServerPacket, event: (data: PlayerCommandEvent) -> Unit)
    fun playerInputEvent(data: Shard_ServerPacket, event: (data: PlayerInputEvent) -> Unit)
    fun selectTradeEvent(data: Shard_ServerPacket, event: (data: SelectTradeEvent) -> Unit)
    fun setCarriedItemEvent(data: Shard_ServerPacket, event: (data: SetCarriedItemEvent) -> Unit)
    fun signUpdateEvent(data: Shard_ServerPacket, event: (data: SignUpdateEvent) -> Unit)
    fun swingArmEvent(data: Shard_ServerPacket, event: (data: SwingArmEvent) -> Unit)
    fun useItemOnEvent(data: Shard_ServerPacket, event: (data: UseItemOnEvent) -> Unit)
    fun useItemEvent(data: Shard_ServerPacket, event: (data: UseItemEvent) -> Unit)
}