package net.crystopia.crystalshard.paper.dhl.types.dialog.buttons

data class ActionButton(
    var button: CommonButtonData,
    var action: Action<*>
)
