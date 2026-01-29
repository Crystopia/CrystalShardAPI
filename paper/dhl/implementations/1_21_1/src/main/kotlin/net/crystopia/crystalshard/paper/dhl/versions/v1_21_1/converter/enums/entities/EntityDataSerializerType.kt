package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.entities

import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.world.entity.player.Player

internal enum class EntityDataSerializerType(val type : EntityDataSerializer<*>) {
    BYTE(EntityDataSerializers.BYTE),
    INT(EntityDataSerializers.INT),
    LONG(EntityDataSerializers.LONG),
    FLOAT(EntityDataSerializers.FLOAT),
    STRING(EntityDataSerializers.STRING),
    COMPONENT(EntityDataSerializers.COMPONENT),
    OPTIONAL_COMPONENT(EntityDataSerializers.OPTIONAL_COMPONENT),
    ITEM_STACK(EntityDataSerializers.ITEM_STACK),
    BLOCK_STATE(EntityDataSerializers.BLOCK_STATE),
    OPTIONAL_BLOCK_STATE(EntityDataSerializers.OPTIONAL_BLOCK_STATE),
    BOOLEAN(EntityDataSerializers.BOOLEAN),
    PARTICLE(EntityDataSerializers.PARTICLE),
    PARTICLES(EntityDataSerializers.PARTICLES),
    ROTATIONS(EntityDataSerializers.ROTATIONS),
    BLOCK_POS(EntityDataSerializers.BLOCK_POS),
    OPTIONAL_BLOCK_POS(EntityDataSerializers.OPTIONAL_BLOCK_POS),
    DIRECTION(EntityDataSerializers.DIRECTION),
    OPTIONAL_GLOBAL_POS(EntityDataSerializers.OPTIONAL_GLOBAL_POS),
    VILLAGER_DATA(EntityDataSerializers.VILLAGER_DATA),
    OPTIONAL_UNSIGNED_INT(EntityDataSerializers.OPTIONAL_UNSIGNED_INT),
    POSE(EntityDataSerializers.POSE),
    CAT_VARIANT(EntityDataSerializers.CAT_VARIANT),
    WOLF_VARIANT(EntityDataSerializers.WOLF_VARIANT),
    FROG_VARIANT(EntityDataSerializers.FROG_VARIANT),
    PAINTING_VARIANT(EntityDataSerializers.PAINTING_VARIANT),
    ARMADILLO_STATE(EntityDataSerializers.ARMADILLO_STATE),
    SNIFFER_STATE(EntityDataSerializers.SNIFFER_STATE),
    VECTOR3(EntityDataSerializers.VECTOR3),
    QUATERNION(EntityDataSerializers.QUATERNION);

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType): EntityDataSerializerType {
            return valueOf(type.name)
        }
    }
}
