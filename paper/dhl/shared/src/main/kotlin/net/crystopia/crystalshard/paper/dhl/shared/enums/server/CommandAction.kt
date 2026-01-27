package net.crystopia.crystalshard.paper.dhl.shared.enums.server

import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket

enum class CommandAction(val id: ServerboundPlayerCommandPacket.Action) {
    STOP_SLEEPING(ServerboundPlayerCommandPacket.Action.STOP_SLEEPING),
    START_SPRINTING(ServerboundPlayerCommandPacket.Action.START_SPRINTING),
    STOP_SPRINTING(ServerboundPlayerCommandPacket.Action.STOP_SPRINTING),
    START_RIDING_JUMP(ServerboundPlayerCommandPacket.Action.START_RIDING_JUMP),
    STOP_RIDING_JUMP(ServerboundPlayerCommandPacket.Action.STOP_RIDING_JUMP),
    OPEN_INVENTORY(ServerboundPlayerCommandPacket.Action.OPEN_INVENTORY),
    START_FALL_FLYING(ServerboundPlayerCommandPacket.Action.START_FALL_FLYING);

    companion object {
        var entries = CommandAction.entries

        fun commandAction(id: ServerboundPlayerCommandPacket.Action): CommandAction? {
            entries.forEach { commandAction ->
                if (commandAction.id == id) {
                    return commandAction
                }
            }
            return null
        }

    }
}