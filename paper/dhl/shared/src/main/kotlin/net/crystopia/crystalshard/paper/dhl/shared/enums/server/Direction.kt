package net.crystopia.crystalshard.paper.dhl.shared.enums.server

enum class Direction {
    DOWN,
    UP,
    NORTH,
    SOUTH,
    WEST,
    EAST;

    companion object {
        fun direction(id: net.minecraft.core.Direction): Direction {
            return valueOf(id.name)
        }
    }
}