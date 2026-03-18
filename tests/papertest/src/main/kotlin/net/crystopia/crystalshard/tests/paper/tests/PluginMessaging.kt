package net.crystopia.crystalshard.tests.paper.tests

import com.google.common.io.ByteStreams
import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.core.utils.Log
import net.crystopia.crystalshard.paper.custom.messaging.ChannelType
import net.crystopia.crystalshard.paper.custom.messaging.PluginMessage
import net.crystopia.crystalshard.tests.paper.CrystalShardPluginTest
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PluginMessaging(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {

    override fun command() {
        test {
            PluginMessage(
                channelType = ChannelType.IN,
                plugin = CrystalShardPluginTest.instance,
                channel = "testy:testy",
                messageType = "testy",
                onMessage = fun(
                    channel: String,
                    player: Player,
                    message: ByteArray,
                ) {
                    val data = ByteStreams.newDataInput(message);
                    val subchannel = data.readUTF();
                    if (subchannel.equals("testy")) {
                        // This is our response to the PlayerCount request
                        val testy = data.readUTF();
                        Log.info("$testy")
                    }
                }
            ).register()
        }
    }

}