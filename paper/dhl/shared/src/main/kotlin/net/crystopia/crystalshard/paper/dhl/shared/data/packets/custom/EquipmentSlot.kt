package net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom

enum class EquipmentSlot(val type: net.minecraft.world.entity.EquipmentSlot) {
    MAINHAND(net.minecraft.world.entity.EquipmentSlot.OFFHAND),
    OFFHAND(net.minecraft.world.entity.EquipmentSlot.OFFHAND),
    FEET(net.minecraft.world.entity.EquipmentSlot.FEET),
    LEGS(net.minecraft.world.entity.EquipmentSlot.LEGS),
    CHEST(net.minecraft.world.entity.EquipmentSlot.CHEST),
    HEAD(net.minecraft.world.entity.EquipmentSlot.HEAD),
    BODY(net.minecraft.world.entity.EquipmentSlot.BODY),
    SADDLE(net.minecraft.world.entity.EquipmentSlot.SADDLE);
}