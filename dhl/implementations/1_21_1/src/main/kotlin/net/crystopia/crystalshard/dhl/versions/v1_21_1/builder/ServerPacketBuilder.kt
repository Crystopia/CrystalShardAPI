package net.crystopia.crystalshard.dhl.versions.v1_21_1.builder

import net.crystopia.crystalshard.dhl.shared.ServerPacket
import net.crystopia.crystalshard.dhl.shared.data.gui.Slot
import net.crystopia.crystalshard.dhl.shared.data.packets.server.*
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IServerPacketBuilder
import net.crystopia.crystalshard.dhl.versions.v1_21_1.server.*

object ServerPacketBuilder : IServerPacketBuilder {

    override fun seenAdvancementsEvent(
        data: ServerPacket,
        event: (data: SeenAdvancementsEvent) -> Unit
    ) {
        Shard_ServerboundSeenAdvancementsPacket().onEvent(data, event)
    }

    override fun acceptTeleportationEvent(
        data: ServerPacket,
        event: (id: Int) -> Unit
    ) {
        Shard_ServerboundAcceptTeleportationPacket().onEvent(data, event)
    }

    override fun containerButtonClickEvent(
        data: ServerPacket,
        event: (data: ButtonClickEvent) -> Unit
    ) {
        Shard_ServerboundContainerButtonClickPacket().onEvent(data, event)
    }

    override fun containerClickEvent(
        data: ServerPacket,
        items: MutableList<Slot>,
        event: (data: ContainerClickEvent) -> Unit
    ) {
        Shard_ServerboundContainerClickPacket(items).onEvent(data, event)
    }

    override fun customClickActionEvent(
        data: ServerPacket,
        event: (data: CustomClickEvent) -> Unit
    ) {
        throw NoPacketMethodFound("customClickActionEvent")
    }

    override fun interactEvent(
        data: ServerPacket,
        event: (data: InteractEvent) -> Unit
    ) {
        Shard_ServerboundInteractPacket().onEvent(data, event)
    }

    override fun movePlayerEvent(
        data: ServerPacket,
        event: (data: MovePlayerEvent) -> Unit
    ) {
        Shard_ServerboundMovePlayerPacket().onEvent(data, event)
    }

    override fun moveVehicleEvent(
        data: ServerPacket,
        event: (data: MoveVehicleEvent) -> Unit
    ) {
        Shard_ServerboundMoveVehiclePacket().onEvent(data, event)
    }

    override fun playerActionEvent(
        data: ServerPacket,
        event: (data: PlayerActionEvent) -> Unit
    ) {
        Shard_ServerboundPlayerActionPacket().onEvent(data, event)
    }

    override fun playerCommandEvent(
        data: ServerPacket,
        event: (data: PlayerCommandEvent) -> Unit
    ) {
        Shard_ServerboundPlayerCommandPacket().onEvent(data, event)
    }

    override fun playerInputEvent(
        data: ServerPacket,
        event: (data: PlayerInputEvent) -> Unit
    ) {
        Shard_ServerboundPlayerInputPacket().onEvent(data, event)
    }

    override fun selectTradeEvent(
        data: ServerPacket,
        event: (data: SelectTradeEvent) -> Unit
    ) {
        Shard_ServerboundSelectTradePacket().onEvent(data, event)
    }

    override fun setCarriedItemEvent(
        data: ServerPacket,
        event: (data: SetCarriedItemEvent) -> Unit
    ) {
        Shard_ServerboundSetCarriedItemPacket().onEvent(data, event)
    }

    override fun signUpdateEvent(
        data: ServerPacket,
        event: (data: SignUpdateEvent) -> Unit
    ) {
        Shard_ServerboundSignUpdatePacket().onEvent(data, event)
    }

    override fun swingArmEvent(
        data: ServerPacket,
        event: (data: SwingArmEvent) -> Unit
    ) {
        Shard_ServerboundSwingPacket().onEvent(data, event)
    }

    override fun useItemOnEvent(
        data: ServerPacket,
        event: (data: UseItemOnEvent) -> Unit
    ) {
        Shard_ServerboundUseItemOnPacket().onEvent(data, event)
    }

    override fun useItemEvent(
        data: ServerPacket,
        event: (data: UseItemEvent) -> Unit
    ) {
        Shard_ServerboundUseItemPacket().onEvent(data, event)
    }
}