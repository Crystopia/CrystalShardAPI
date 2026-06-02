package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.panic.extension.clientMods
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ClientModTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            (sender as Player).clientMods("""
                {id:"minecraft:oak_sign",front_text:{messages:['{"translate":"text.skinlayers.title","fallback":"NONE"}','{"text":""}','{"text":""}','{"text":""}']}}
            """.trimIndent()) {
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