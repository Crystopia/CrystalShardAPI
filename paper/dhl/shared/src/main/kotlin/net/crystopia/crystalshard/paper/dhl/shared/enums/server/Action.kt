package net.crystopia.crystalshard.paper.dhl.shared.enums.server

enum class Action {
    START_DESTROY_BLOCK,
    ABORT_DESTROY_BLOCK,
    STOP_DESTROY_BLOCK,
    DROP_ALL_ITEMS,
    DROP_ITEM,
    RELEASE_USE_ITEM,
    SWAP_ITEM_WITH_OFFHAND;

    companion object {
        val entries = Action.entries

        fun action(id: String): Action? {
            entries.forEach { direction ->
                if (direction.name.equals(id, ignoreCase = true))
                    return direction
            }
            return null
        }
    }
}