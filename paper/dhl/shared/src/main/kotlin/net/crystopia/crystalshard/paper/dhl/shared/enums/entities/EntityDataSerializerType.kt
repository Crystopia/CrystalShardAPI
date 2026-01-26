package net.crystopia.crystalshard.paper.dhl.shared.enums.entities

import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil

enum class EntityDataSerializerType {
    BYTE,
    INT,
    LONG,
    FLOAT,
    STRING,
    COMPONENT,
    OPTIONAL_COMPONENT,
    ITEM_STACK,
    BLOCK_STATE,
    OPTIONAL_BLOCK_STATE,
    BOOLEAN,
    PARTICLE,
    PARTICLES,
    ROTATIONS,
    BLOCK_POS,
    OPTIONAL_BLOCK_POS,
    DIRECTION,
    OPTIONAL_LIVING_ENTITY_REFERENCE,
    OPTIONAL_GLOBAL_POS,
    VILLAGER_DATA,
    OPTIONAL_UNSIGNED_INT,
    POSE,
    CAT_VARIANT,
    CHICKEN_VARIANT,
    COW_VARIANT,
    WOLF_VARIANT,
    WOLF_SOUND_VARIANT,
    FROG_VARIANT,
    PIG_VARIANT,
    PAINTING_VARIANT,
    ARMADILLO_STATE,
    SNIFFER_STATE,
    WEATHERING_COPPER_STATE,
    COPPER_GOLEM_STATE,
    VECTOR3,
    QUATERNION,
    RESOLVABLE_PROFILE;
}
