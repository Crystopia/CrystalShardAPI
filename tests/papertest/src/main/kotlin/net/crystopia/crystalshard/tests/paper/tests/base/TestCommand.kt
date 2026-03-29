package net.crystopia.crystalshard.tests.paper.tests.base

import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.literalArgument
import net.crystopia.crystalshard.tests.paper.tests.*

object TestCommand {

    val command = commandTree("test") {
        literalArgument("plugin-messaging") {
            anyExecutor { sender, arguments ->
                PluginMessaging("plugin-messaging", sender, arguments).command()
            }
        }

        literalArgument("advancement") {
            anyExecutor { sender, arguments ->
                AdvancementTest("advancement", sender, arguments).command()
            }
        }

        literalArgument("component") {
            anyExecutor { sender, arguments ->
                ComponentTest("component", sender, arguments).command()
            }
        }

        literalArgument("world") {
            anyExecutor { sender, arguments ->
                WorldTest("world", sender, arguments).command()
            }
        }

        literalArgument("invfont") {
            anyExecutor { sender, arguments ->
                InvFontTest("invfont", sender, arguments).command()
            }
        }

        literalArgument("cooldown") {
            anyExecutor { sender, arguments ->
                CooldownTest("cooldown", sender, arguments).command()
            }
        }

        literalArgument("clientmod") {
            anyExecutor { sender, arguments ->
                ClientModTest("clientmod", sender, arguments).command()
            }
        }

        literalArgument("dialog") {
            anyExecutor { sender, arguments ->
                DialogTest("dialog", sender, arguments).command()
            }
        }

        literalArgument("mob-effect") {
            anyExecutor { sender, arguments ->
                MobEffectTest("mob-effect", sender, arguments).command()
            }
        }

        literalArgument("team") {
            anyExecutor { sender, arguments ->
                TeamTest("team", sender, arguments).command()
            }
        }

        literalArgument("particle") {
            anyExecutor { sender, arguments ->
                ParticleTest("particle", sender, arguments).command()
            }
        }

        literalArgument("teleport") {
            anyExecutor { sender, arguments ->
                TeleportTest("teleport", sender, arguments).command()
            }
        }

        literalArgument("world-border") {
            anyExecutor { sender, arguments ->
                WorldborderTest("world-border", sender, arguments).command()
            }
        }

        literalArgument("game-event") {
            anyExecutor { sender, arguments ->
                GameEventTest("game-event", sender, arguments).command()
            }
        }

        literalArgument("animate") {
            anyExecutor { sender, arguments ->
                AnimateTest("animate", sender, arguments).command()
            }
        }

        literalArgument("packet-gui") {
            anyExecutor { sender, arguments ->
                PacketGUITest("packet-gui", sender, arguments).command()
            }
        }

        literalArgument("packet-dialog") {
            anyExecutor { sender, arguments ->
                PacketDialogTest("packet-dialog", sender, arguments).command()
            }
        }

        literalArgument("merchant") {
            anyExecutor { sender, arguments ->
                MerchantTest("merchant", sender, arguments).command()
            }
        }

        literalArgument("scoreboard") {
            anyExecutor { sender, arguments ->
                ScoreboardTest("scoreboard", sender, arguments).command()
            }
        }

        literalArgument("waypoint") {
            anyExecutor { sender, arguments ->
                WaypointTest("waypoint", sender, arguments).command()
            }
        }

        literalArgument("pack") {
            anyExecutor { sender, arguments ->
                PlayerHeadTest("pack", sender, arguments).command()
            }
        }

        literalArgument("display") {
            anyExecutor { sender, arguments ->
                DisplayTest("display", sender, arguments).command()
            }
        }

        literalArgument("npc") {
            anyExecutor { sender, arguments ->
                NPCTest("npc", sender, arguments).command()
            }
        }
    }

}