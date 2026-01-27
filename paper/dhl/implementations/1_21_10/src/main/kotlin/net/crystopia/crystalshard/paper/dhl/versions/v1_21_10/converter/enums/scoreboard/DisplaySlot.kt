package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.scoreboard

enum class DisplaySlot(val id : net.minecraft.world.scores.DisplaySlot) {
    LIST(net.minecraft.world.scores.DisplaySlot.LIST),
    SIDEBAR(net.minecraft.world.scores.DisplaySlot.SIDEBAR),
    BELOW_NAME(net.minecraft.world.scores.DisplaySlot.BELOW_NAME),
    TEAM_BLACK(net.minecraft.world.scores.DisplaySlot.TEAM_BLACK),
    TEAM_DARK_BLUE(net.minecraft.world.scores.DisplaySlot.TEAM_DARK_BLUE),
    TEAM_DARK_GREEN(net.minecraft.world.scores.DisplaySlot.TEAM_DARK_GREEN),
    TEAM_DARK_AQUA(net.minecraft.world.scores.DisplaySlot.TEAM_DARK_AQUA),
    TEAM_DARK_RED(net.minecraft.world.scores.DisplaySlot.TEAM_DARK_RED),
    TEAM_DARK_PURPLE(net.minecraft.world.scores.DisplaySlot.TEAM_DARK_PURPLE),
    TEAM_GOLD(net.minecraft.world.scores.DisplaySlot.TEAM_GOLD),
    TEAM_GRAY(net.minecraft.world.scores.DisplaySlot.TEAM_GRAY),
    TEAM_DARK_GRAY(net.minecraft.world.scores.DisplaySlot.TEAM_DARK_GRAY),
    TEAM_BLUE(net.minecraft.world.scores.DisplaySlot.TEAM_BLUE),
    TEAM_GREEN(net.minecraft.world.scores.DisplaySlot.TEAM_GREEN),
    TEAM_AQUA(net.minecraft.world.scores.DisplaySlot.TEAM_AQUA),
    TEAM_RED(net.minecraft.world.scores.DisplaySlot.TEAM_RED),
    TEAM_LIGHT_PURPLE(net.minecraft.world.scores.DisplaySlot.TEAM_LIGHT_PURPLE),
    TEAM_YELLOW(net.minecraft.world.scores.DisplaySlot.TEAM_YELLOW),
    TEAM_WHITE(net.minecraft.world.scores.DisplaySlot.TEAM_WHITE);

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.DisplaySlot): DisplaySlot {
            return valueOf(
                type.name
            )
        }
    }

}
