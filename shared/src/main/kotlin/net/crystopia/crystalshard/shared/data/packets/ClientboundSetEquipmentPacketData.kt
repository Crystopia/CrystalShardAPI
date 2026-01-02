package net.crystopia.crystalshard.shared.data.packets

import com.mojang.datafixers.util.Pair
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack

data class ClientboundSetEquipmentPacketData(
    var entityId: Int,
    var equipmentList: MutableList<Pair<EquipmentSlot, ItemStack>>
) 