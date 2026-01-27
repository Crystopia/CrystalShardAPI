package net.crystopia.crystalshard.paper.dhl.shared.enums.server

enum class ClickType(open var id: Int) {
    PICKUP(0), QUICK_MOVE(1), SWAP(2), CLONE(3), THROW(4), QUICK_CRAFT(5), PICKUP_ALL(6),
    // OWN
    SET_DOWN(7);

    companion object {
        private val map = ClickType.entries
        fun getType(id: Int): ClickType {
            return map[id]
        }
    }
}