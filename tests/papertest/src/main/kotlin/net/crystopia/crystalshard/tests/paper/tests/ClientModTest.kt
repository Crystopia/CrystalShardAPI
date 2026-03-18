package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.panic.extension.clientMods
import net.crystopia.crystalshard.tests.paper.CrystalShardPluginTest
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ClientModTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            (sender as Player).clientMods(
                CrystalShardPluginTest.instance
            ) {
                onMod("text.skinlayers.title") {
                    check { hasMod ->
                        (sender as Player).sendMessage("Has Mod: $hasMod")
                        if (hasMod)
                            disconnect()
                    }
                }
            }
        }
    }

}