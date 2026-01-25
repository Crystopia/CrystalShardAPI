package net.crystopia.crystalshard.paper.dhl.shared.enums.dialog

import net.minecraft.server.dialog.DialogAction

enum class DialogAction(val base: DialogAction) {
    CLOSE(DialogAction.CLOSE),
    NONE(DialogAction.NONE),
    WAIT_FOR_RESPONSE(DialogAction.WAIT_FOR_RESPONSE);
}