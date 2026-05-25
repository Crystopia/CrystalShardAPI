package net.crystopia.crystalshard.dhl.shared.data.packets.server

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey


data class CustomClickEvent(
    var key: NamespacedKey, var payload: Payload
)

data class Payload(
    var id: Byte, var type: PayloadType
)

data class PayloadType(
    var prettyName: String, var name: String, var data: MutableMap<String, Any>
)