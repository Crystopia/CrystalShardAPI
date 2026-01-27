package net.crystopia.crystalshard.paper.dhl.shared.enums.server

import net.minecraft.world.phys.HitResult

enum class ItemUseType(val id: HitResult.Type) {
    MISS(HitResult.Type.MISS),
    BLOCK(HitResult.Type.BLOCK),
    ENTITY(HitResult.Type.ENTITY);

    companion object {
        var entries = ItemUseType.entries

        fun itemUseType(id: HitResult.Type): ItemUseType? {
            entries.forEach { itemUseType ->
                if (itemUseType.id == id) {
                    return itemUseType
                }
            }
            return null
        }
    }
}