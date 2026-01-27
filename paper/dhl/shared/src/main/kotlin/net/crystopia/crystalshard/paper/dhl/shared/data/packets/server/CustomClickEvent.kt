package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

import net.kyori.adventure.key.Key

data class CustomClickEvent(
    var key: Key, var payload: Payload
)

data class Payload(
    var id: Byte, var type: PayloadType
)

data class PayloadType(
    var prettyName: String, var name: String, var data: MutableMap<String, Any>
)