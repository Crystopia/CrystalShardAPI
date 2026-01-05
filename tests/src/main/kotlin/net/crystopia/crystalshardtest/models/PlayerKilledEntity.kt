package net.crystopia.crystalshardtest.models

import kotlinx.serialization.Serializable

@Serializable
data class PlayerKilledEntity(
    var player: PlayerKilledEntityPlayer,
    var entity: PlayerKilledEntityEntity
)


@Serializable
data class PlayerKilledEntityPlayer(
    var type: String
)

@Serializable
data class PlayerKilledEntityEntity(
    var type: String
)