package net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.enums.dialog

internal enum class DialogAction(val base: net.minecraft.server.dialog.DialogAction) {
    CLOSE(net.minecraft.server.dialog.DialogAction.CLOSE),
    NONE(net.minecraft.server.dialog.DialogAction.NONE),
    WAIT_FOR_RESPONSE(net.minecraft.server.dialog.DialogAction.WAIT_FOR_RESPONSE);

    companion object {
        fun convert(type: net.crystopia.crystalshard.dhl.shared.enums.dialog.DialogAction): DialogAction {
            return valueOf(type.name)
        }
    }

}