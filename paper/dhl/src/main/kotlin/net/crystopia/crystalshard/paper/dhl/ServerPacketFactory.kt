package net.crystopia.crystalshard.paper.dhl

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.ButtonClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.ContainerClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.CustomClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.InteractEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.MovePlayerEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.MoveVehicleEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.PlayerActionEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.PlayerCommandEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.PlayerInputEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.SelectTradeEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.SetCarriedItemEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.SignUpdateEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.SwingArmEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.UseItemEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.UseItemOnEvent
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundContainerButtonClickPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundContainerClickPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundCustomClickActionPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundInteractPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundMovePlayerPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundMoveVehiclePacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundPlayerActionPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundPlayerCommandPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundPlayerInputPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundSelectTradePacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundSetCarriedItemPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundSignUpdatePacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundSwingPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundUseItemOnPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundUseItemPacket
import net.minecraft.world.entity.player.Player
import org.bukkit.inventory.ItemStack

object ServerPacketFactory {

    fun containerClickEvent(
        items: MutableMap<Int, ItemStack>, data: Shard_ServerPacketData, callback: ContainerClickEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundContainerClickPacket(items).attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundContainerClickPacket(items)
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun containerButtonClickEvent(
        data: Shard_ServerPacketData, callback: ButtonClickEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundContainerButtonClickPacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundContainerButtonClickPacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun customClickActionEvent(
        data: Shard_ServerPacketData, callback: CustomClickEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundCustomClickActionPacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                throw Exception("CustomClickAction Events are not available in 1.21.1")
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun interactEvent(
        data: Shard_ServerPacketData, callback: InteractEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundInteractPacket().attach(data, callback)
            }
            
            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundInteractPacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun movePlayerEvent(
        data: Shard_ServerPacketData, callback: MovePlayerEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundMovePlayerPacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundMovePlayerPacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun moveVehicleEvent(
        data: Shard_ServerPacketData, callback: MoveVehicleEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundMoveVehiclePacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundMoveVehiclePacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun playerActionEvent(
        data: Shard_ServerPacketData, callback: PlayerActionEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundPlayerActionPacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundPlayerActionPacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun playerCommandEvent(
        data: Shard_ServerPacketData, callback: PlayerCommandEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundPlayerCommandPacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundPlayerCommandPacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun playerInputEvent(
        data: Shard_ServerPacketData, callback: PlayerInputEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundPlayerInputPacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundPlayerInputPacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun selectTradeEvent(
        data: Shard_ServerPacketData, callback: SelectTradeEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundSelectTradePacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundSelectTradePacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun setCarriedItemEvent(
        data: Shard_ServerPacketData, callback: SetCarriedItemEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundSetCarriedItemPacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundSetCarriedItemPacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun signUpdateEvent(
        data: Shard_ServerPacketData, callback: SignUpdateEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundSignUpdatePacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundSignUpdatePacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun swingArmEvent(
        data: Shard_ServerPacketData, callback: SwingArmEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundSwingPacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundSwingPacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun useItemOnEvent(
        data: Shard_ServerPacketData, callback: UseItemOnEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundUseItemOnPacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundUseItemOnPacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }

    fun useItemEvent(
        data: Shard_ServerPacketData, callback: UseItemEvent.() -> Unit
    ) {
        when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                Shard_ServerboundUseItemPacket().attach(data, callback)
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundUseItemPacket()
                    .attach(data, callback)
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }
    }
}