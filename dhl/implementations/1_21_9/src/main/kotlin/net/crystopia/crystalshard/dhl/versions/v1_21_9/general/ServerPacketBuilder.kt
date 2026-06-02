package net.crystopia.crystalshard.dhl.versions.v1_21_9.general

import net.crystopia.crystalshard.dhl.shared.Shard_ServerPacket
import net.crystopia.crystalshard.dhl.shared.data.gui.Slot
import net.crystopia.crystalshard.dhl.shared.data.packets.server.*
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacketBuilder
import net.crystopia.crystalshard.dhl.versions.v1_21_9.server.*

object ServerPacketBuilder : IServerPacketBuilder {
    override fun acceptTeleportationEvent(
        data: Shard_ServerPacket,
        event: (id: Int) -> Unit
    ) {
        Shard_ServerboundAcceptTeleportationPacket().onEvent(data, event)
    }

    override fun containerButtonClickEvent(
        data: Shard_ServerPacket,
        event: (data: ButtonClickEvent) -> Unit
    ) {
        Shard_ServerboundContainerButtonClickPacket().onEvent(data, event)
    }

    override fun containerClickEvent(
        data: Shard_ServerPacket,
        items: MutableList<Slot>,
        event: (data: ContainerClickEvent) -> Unit
    ) {
        Shard_ServerboundContainerClickPacket(items).onEvent(data, event)
    }

    override fun customClickActionEvent(
        data: Shard_ServerPacket,
        event: (data: CustomClickEvent) -> Unit
    ) {
        Shard_ServerboundCustomClickActionPacket().onEvent(data, event)
    }

    override fun interactEvent(
        data: Shard_ServerPacket,
        event: (data: InteractEvent) -> Unit
    ) {
        Shard_ServerboundInteractPacket().onEvent(data, event)
    }

    override fun movePlayerEvent(
        data: Shard_ServerPacket,
        event: (data: MovePlayerEvent) -> Unit
    ) {
        Shard_ServerboundMovePlayerPacket().onEvent(data, event)
    }

    override fun moveVehicleEvent(
        data: Shard_ServerPacket,
        event: (data: MoveVehicleEvent) -> Unit
    ) {
        Shard_ServerboundMoveVehiclePacket().onEvent(data, event)
    }

    override fun playerActionEvent(
        data: Shard_ServerPacket,
        event: (data: PlayerActionEvent) -> Unit
    ) {
        Shard_ServerboundPlayerActionPacket().onEvent(data, event)
    }

    override fun playerCommandEvent(
        data: Shard_ServerPacket,
        event: (data: PlayerCommandEvent) -> Unit
    ) {
        Shard_ServerboundPlayerCommandPacket().onEvent(data, event)
    }

    override fun playerInputEvent(
        data: Shard_ServerPacket,
        event: (data: PlayerInputEvent) -> Unit
    ) {
        Shard_ServerboundPlayerInputPacket().onEvent(data, event)
    }

    override fun selectTradeEvent(
        data: Shard_ServerPacket,
        event: (data: SelectTradeEvent) -> Unit
    ) {
        Shard_ServerboundSelectTradePacket().onEvent(data, event)
    }

    override fun setCarriedItemEvent(
        data: Shard_ServerPacket,
        event: (data: SetCarriedItemEvent) -> Unit
    ) {
        Shard_ServerboundSetCarriedItemPacket().onEvent(data, event)
    }

    override fun signUpdateEvent(
        data: Shard_ServerPacket,
        event: (data: SignUpdateEvent) -> Unit
    ) {
        Shard_ServerboundSignUpdatePacket().onEvent(data, event)
    }

    override fun swingArmEvent(
        data: Shard_ServerPacket,
        event: (data: SwingArmEvent) -> Unit
    ) {
        Shard_ServerboundSwingPacket().onEvent(data, event)
    }

    override fun useItemOnEvent(
        data: Shard_ServerPacket,
        event: (data: UseItemOnEvent) -> Unit
    ) {
        Shard_ServerboundUseItemOnPacket().onEvent(data, event)
    }

    override fun useItemEvent(
        data: Shard_ServerPacket,
        event: (data: UseItemEvent) -> Unit
    ) {
        Shard_ServerboundUseItemPacket().onEvent(data, event)
    }
}