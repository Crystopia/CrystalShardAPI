package net.crystopia.crystalshard.paper.dhl.shared.enums.server

enum class InteractionHand(val id: net.minecraft.world.InteractionHand) {
    MAIN_HAND(net.minecraft.world.InteractionHand.MAIN_HAND),
    OFF_HAND(net.minecraft.world.InteractionHand.OFF_HAND);

    companion object {
        var entries = InteractionHand.entries

        fun interactionHand(id: net.minecraft.world.InteractionHand): InteractionHand? {
            entries.forEach { interactionHand ->
                if (interactionHand.id == id)
                    return interactionHand
            }
            return null
        }

    }

}