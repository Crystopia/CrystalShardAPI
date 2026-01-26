package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.enums.entities

import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.network.syncher.EntityDataSerializers

internal enum class EntityDataSerializerType(val type: EntityDataSerializer<*>) {
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
    OPTIONAL_LIVING_ENTITY_REFERENCE(EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE),
    OPTIONAL_GLOBAL_POS(EntityDataSerializers.OPTIONAL_GLOBAL_POS),
    VILLAGER_DATA(EntityDataSerializers.VILLAGER_DATA),
    OPTIONAL_UNSIGNED_INT(EntityDataSerializers.OPTIONAL_UNSIGNED_INT),
    POSE(EntityDataSerializers.POSE),
    CAT_VARIANT(EntityDataSerializers.CAT_VARIANT),
    CHICKEN_VARIANT(EntityDataSerializers.CHICKEN_VARIANT),
    COW_VARIANT(EntityDataSerializers.COW_VARIANT),
    WOLF_VARIANT(EntityDataSerializers.WOLF_VARIANT),
    WOLF_SOUND_VARIANT(EntityDataSerializers.WOLF_SOUND_VARIANT),
    FROG_VARIANT(EntityDataSerializers.FROG_VARIANT),
    PIG_VARIANT(EntityDataSerializers.PIG_VARIANT),
    PAINTING_VARIANT(EntityDataSerializers.PAINTING_VARIANT),
    ARMADILLO_STATE(EntityDataSerializers.ARMADILLO_STATE),
    SNIFFER_STATE(EntityDataSerializers.SNIFFER_STATE),
    WEATHERING_COPPER_STATE(EntityDataSerializers.WEATHERING_COPPER_STATE),
    COPPER_GOLEM_STATE(EntityDataSerializers.COPPER_GOLEM_STATE),
    VECTOR3(EntityDataSerializers.VECTOR3),
    QUATERNION(EntityDataSerializers.QUATERNION),
    RESOLVABLE_PROFILE(EntityDataSerializers.RESOLVABLE_PROFILE);

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType): EntityDataSerializerType {
            return valueOf(type.name)
        }
    }

}
