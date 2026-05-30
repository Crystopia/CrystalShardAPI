package net.crystopia.crystalshard.paper.dhl.types.dialog.buttons

data class ActionButton(
    var button: net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.CommonButtonData,
    var action: Action<*>
)

fun ActionButton.toDhl(): net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionButton {
    return net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionButton(
        button = this.button.toDhl(),
        action = this.action.toDhl()
    )
}