package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.entities

import net.minecraft.world.level.block.entity.BlockEntityType

enum class BlockEntityType(val type: BlockEntityType<*>) {
    FURNACE(BlockEntityType.FURNACE),
    CHEST(BlockEntityType.CHEST),
    TRAPPED_CHEST(BlockEntityType.TRAPPED_CHEST),
    ENDER_CHEST(BlockEntityType.ENDER_CHEST),
    JUKEBOX(BlockEntityType.JUKEBOX),
    DISPENSER(BlockEntityType.DISPENSER),
    DROPPER(BlockEntityType.DROPPER),
    SIGN(BlockEntityType.SIGN),
    HANGING_SIGN(BlockEntityType.HANGING_SIGN),
    MOB_SPAWNER(BlockEntityType.MOB_SPAWNER),
    PISTON(BlockEntityType.PISTON),
    BREWING_STAND(BlockEntityType.BREWING_STAND),
    ENCHANTING_TABLE(BlockEntityType.ENCHANTING_TABLE),
    END_PORTAL(BlockEntityType.END_PORTAL),
    BEACON(BlockEntityType.BEACON),
    SKULL(BlockEntityType.SKULL),
    DAYLIGHT_DETECTOR(BlockEntityType.DAYLIGHT_DETECTOR),
    HOPPER(BlockEntityType.HOPPER),
    COMPARATOR(BlockEntityType.COMPARATOR),
    BANNER(BlockEntityType.BANNER),
    STRUCTURE_BLOCK(BlockEntityType.STRUCTURE_BLOCK),
    END_GATEWAY(BlockEntityType.END_GATEWAY),
    COMMAND_BLOCK(BlockEntityType.COMMAND_BLOCK),
    SHULKER_BOX(BlockEntityType.SHULKER_BOX),
    BED(BlockEntityType.BED),
    CONDUIT(BlockEntityType.CONDUIT),
    BARREL(BlockEntityType.BARREL),
    SMOKER(BlockEntityType.SMOKER),
    BLAST_FURNACE(BlockEntityType.BLAST_FURNACE),
    LECTERN(BlockEntityType.LECTERN),
    BELL(BlockEntityType.BELL),
    JIGSAW(BlockEntityType.JIGSAW),
    CAMPFIRE(BlockEntityType.CAMPFIRE),
    BEEHIVE(BlockEntityType.BEEHIVE),
    SCULK_SENSOR(BlockEntityType.SCULK_SENSOR),
    CALIBRATED_SCULK_SENSOR(BlockEntityType.CALIBRATED_SCULK_SENSOR),
    SCULK_CATALYST(BlockEntityType.SCULK_CATALYST),
    SCULK_SHRIEKER(BlockEntityType.SCULK_SHRIEKER),
    CHISELED_BOOKSHELF(BlockEntityType.CHISELED_BOOKSHELF),
    BRUSHABLE_BLOCK(BlockEntityType.BRUSHABLE_BLOCK),
    DECORATED_POT(BlockEntityType.DECORATED_POT),
    CRAFTER(BlockEntityType.CRAFTER),
    TRIAL_SPAWNER(BlockEntityType.TRIAL_SPAWNER),
    VAULT(BlockEntityType.VAULT);

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.entities.BlockEntityType): net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.entities.BlockEntityType {
            return net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.entities.BlockEntityType.valueOf(
                type.name
            )
        }
    }

}