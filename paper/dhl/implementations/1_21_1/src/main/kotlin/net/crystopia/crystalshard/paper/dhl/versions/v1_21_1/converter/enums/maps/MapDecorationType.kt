package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.maps

import net.minecraft.core.Holder
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes

enum class MapDecorationType(val id : Holder<net.minecraft.world.level.saveddata.maps.MapDecorationType>) {
    PLAYER(MapDecorationTypes.PLAYER),
    FRAME(MapDecorationTypes.FRAME),
    RED_MARKER(MapDecorationTypes.RED_MARKER),
    BLUE_MARKER(MapDecorationTypes.BLUE_MARKER),
    TARGET_X(MapDecorationTypes.TARGET_X),
    TARGET_POINT(MapDecorationTypes.TARGET_POINT),
    PLAYER_OFF_MAP(MapDecorationTypes.PLAYER_OFF_MAP),
    PLAYER_OFF_LIMITS(MapDecorationTypes.PLAYER_OFF_LIMITS),
    WOODLAND_MANSION(MapDecorationTypes.WOODLAND_MANSION),
    OCEAN_MONUMENT(MapDecorationTypes.OCEAN_MONUMENT),
    WHITE_BANNER(MapDecorationTypes.WHITE_BANNER),
    ORANGE_BANNER(MapDecorationTypes.ORANGE_BANNER),
    MAGENTA_BANNER(MapDecorationTypes.MAGENTA_BANNER),
    LIGHT_BLUE_BANNER(MapDecorationTypes.LIGHT_BLUE_BANNER),
    YELLOW_BANNER(MapDecorationTypes.YELLOW_BANNER),
    LIME_BANNER(MapDecorationTypes.LIME_BANNER),
    PINK_BANNER(MapDecorationTypes.PINK_BANNER),
    GRAY_BANNER(MapDecorationTypes.GRAY_BANNER),
    LIGHT_GRAY_BANNER(MapDecorationTypes.LIGHT_GRAY_BANNER),
    CYAN_BANNER(MapDecorationTypes.CYAN_BANNER),
    PURPLE_BANNER(MapDecorationTypes.PURPLE_BANNER),
    BLUE_BANNER(MapDecorationTypes.BLUE_BANNER),
    BROWN_BANNER(MapDecorationTypes.BROWN_BANNER),
    GREEN_BANNER(MapDecorationTypes.GREEN_BANNER),
    RED_BANNER(MapDecorationTypes.RED_BANNER),
    BLACK_BANNER(MapDecorationTypes.BLACK_BANNER),
    RED_X(MapDecorationTypes.RED_X),
    DESERT_VILLAGE(MapDecorationTypes.DESERT_VILLAGE),
    PLAINS_VILLAGE(MapDecorationTypes.PLAINS_VILLAGE),
    SAVANNA_VILLAGE(MapDecorationTypes.SAVANNA_VILLAGE),
    SNOWY_VILLAGE(MapDecorationTypes.SNOWY_VILLAGE),
    TAIGA_VILLAGE(MapDecorationTypes.TAIGA_VILLAGE),
    JUNGLE_TEMPLE(MapDecorationTypes.JUNGLE_TEMPLE),
    SWAMP_HUT(MapDecorationTypes.SWAMP_HUT),
    TRIAL_CHAMBERS(MapDecorationTypes.TRIAL_CHAMBERS);

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.maps.MapDecorationType): MapDecorationType {
            return MapDecorationType.valueOf(
                type.name
            )
        }
    }

}