package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.custom.world.customWorld
import net.crystopia.crystalshard.paper.custom.world.data.WorldSettings
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.NamespacedKey
import org.bukkit.World
import org.bukkit.WorldType
import org.bukkit.command.CommandSender

class WorldTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            customWorld {
                name(NamespacedKey("custom", "jesper"))
                settings(
                    WorldSettings(
                        seed = 4534545,
                        environment = World.Environment.NORMAL,
                        type = WorldType.NORMAL,
                        generateStructures = true,
                        hardcore = true,
                        bonusChest = true,
                    )
                )
                create {
                    println("CREATED")
                }
            }
        }
    }

}