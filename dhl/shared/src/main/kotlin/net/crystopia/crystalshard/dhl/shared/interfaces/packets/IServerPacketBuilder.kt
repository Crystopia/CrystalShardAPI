package net.crystopia.crystalshard.dhl.shared.interfaces.packets

import net.crystopia.crystalshard.dhl.shared.ServerPacket
import net.crystopia.crystalshard.dhl.shared.data.gui.Slot
import net.crystopia.crystalshard.dhl.shared.data.packets.server.*
import net.crystopia.crystalshard.dhl.shared.enums.packets.SelectAdvancementTabAction

interface IServerPacketBuilder {
    fun seenAdvancementsEvent(data: ServerPacket, event: (data: SeenAdvancementsEvent) -> Unit)
    fun acceptTeleportationEvent(data: ServerPacket, event: (id: Int) -> Unit)
    fun containerButtonClickEvent(data: ServerPacket, event: (data: ButtonClickEvent) -> Unit)
    fun containerClickEvent(
        data: ServerPacket,
        items: MutableList<Slot>,
        event: (data: ContainerClickEvent) -> Unit
    )

    fun customClickActionEvent(data: ServerPacket, event: (data: CustomClickEvent) -> Unit)
    fun interactEvent(data: ServerPacket, event: (data: InteractEvent) -> Unit)
    fun movePlayerEvent(data: ServerPacket, event: (data: MovePlayerEvent) -> Unit)
    fun moveVehicleEvent(data: ServerPacket, event: (data: MoveVehicleEvent) -> Unit)
    fun playerActionEvent(data: ServerPacket, event: (data: PlayerActionEvent) -> Unit)
    fun playerCommandEvent(data: ServerPacket, event: (data: PlayerCommandEvent) -> Unit)
    fun playerInputEvent(data: ServerPacket, event: (data: PlayerInputEvent) -> Unit)
    fun selectTradeEvent(data: ServerPacket, event: (data: SelectTradeEvent) -> Unit)
    fun setCarriedItemEvent(data: ServerPacket, event: (data: SetCarriedItemEvent) -> Unit)
    fun signUpdateEvent(data: ServerPacket, event: (data: SignUpdateEvent) -> Unit)
    fun swingArmEvent(data: ServerPacket, event: (data: SwingArmEvent) -> Unit)
    fun useItemOnEvent(data: ServerPacket, event: (data: UseItemOnEvent) -> Unit)
    fun useItemEvent(data: ServerPacket, event: (data: UseItemEvent) -> Unit)
}