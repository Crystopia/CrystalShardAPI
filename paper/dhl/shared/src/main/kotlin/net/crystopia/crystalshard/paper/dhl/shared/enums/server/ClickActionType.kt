package net.crystopia.crystalshard.paper.dhl.shared.enums.server

enum class ClickActionType(name: String) {

    RIGHT_CLICK("rightClick"), LEFT_CLICK("leftClick"), SHIFT_RIGHT_CLICK("shiftRightClick"), SHIFT_LEFT_CLICK("shiftLeftClick"), UNKNOWN(
        "UNKNOWN"
    );

    companion object {
        fun clickType(isAttack: Boolean, isUsingSecondaryAction: Boolean): ClickActionType {
            return if (isAttack && isUsingSecondaryAction) SHIFT_RIGHT_CLICK
            else if (isAttack) RIGHT_CLICK
            else if (isUsingSecondaryAction) SHIFT_LEFT_CLICK
            else LEFT_CLICK
        }
    }
}