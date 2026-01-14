package net.crystopia.crystalshard.tests.paper

import com.google.common.io.ByteStreams
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import gg.flyte.twilight.twilight
import io.papermc.paper.advancement.AdvancementDisplay
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.core.crystalshard
import net.crystopia.crystalshard.paper.core.utils.Log
import net.crystopia.crystalshard.paper.custom.advancements.Advancement
import net.crystopia.crystalshard.paper.custom.advancements.models.AdvancementModel
import net.crystopia.crystalshard.paper.custom.advancements.models.criteria.AdvancementCriteria
import net.crystopia.crystalshard.paper.custom.advancements.models.criteria.CriteriaTrigger
import net.crystopia.crystalshard.paper.custom.advancements.models.display.AdvancementDisplayIcon
import net.crystopia.crystalshard.paper.custom.advancements.models.rewards.AdvancementRewards
import net.crystopia.crystalshard.paper.custom.crystal.CrystalEvents
import net.crystopia.crystalshard.paper.custom.messaging.ChannelType
import net.crystopia.crystalshard.paper.custom.messaging.PluginMessage
import net.crystopia.crystalshard.tests.paper.events.PlayerJoin
import net.crystopia.crystalshard.tests.paper.models.PlayerKilledEntity
import net.crystopia.crystalshard.tests.paper.models.PlayerKilledEntityEntity
import net.crystopia.crystalshard.tests.paper.models.PlayerKilledEntityPlayer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin(), Listener {

    companion object {
        lateinit var instance: Main
    }

    init {
        instance = this
    }
    
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIPaperConfig(this).silentLogs(true))

    }

    lateinit var adv: Advancement
    
    override fun onEnable() {
        crystalshard(this)

        Bukkit.getPluginManager().registerEvents(this, this);
        CommandAPI.onEnable();
        val twilight = twilight(this)

        TestyCommand
        server.pluginManager.registerEvents(PlayerJoin, this)
        server.pluginManager.registerEvents(CrystalEvents, this)


        PluginMessage(
            channelType = ChannelType.IN,
            plugin = instance,
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

        val list: MutableMap<String, AdvancementCriteria> = mutableMapOf()
        list["test"] = AdvancementCriteria(
            conditions = Json.encodeToJsonElement(
                PlayerKilledEntity(
                    player = PlayerKilledEntityPlayer(
                        type = "minecraft:player"
                    ),
                    entity = PlayerKilledEntityEntity(
                        type = "minecraft:zombie"
                    ),
                )
            ),
            trigger = CriteriaTrigger.PLAYER_KILLED_ENTITY.type,
        )
        adv = Advancement(
            NamespacedKey(
                "crystalshard", "testy",
            ),
            advancementModel = AdvancementModel(
                display = net.crystopia.crystalshard.paper.custom.advancements.models.display.AdvancementDisplay(
                    icon = AdvancementDisplayIcon(
                        id = Material.COMPASS.key.toString(),
                        count = 1,
                        // component = JSONComponentSerializer.builder().build().serialize(Component.text("Cool"))
                    ),
                    title = JSONComponentSerializer.builder().build().serialize(Component.text("Cool")),
                    description = JSONComponentSerializer.builder().build().serialize(Component.text("Cool")),
                    frame = AdvancementDisplay.Frame.TASK,
                    show_toast = false,
                    announce_to_chat = false,
                    hidden = true
                ),
                criteria = list,
                requirements = mutableListOf(mutableListOf("test")),
                rewards = AdvancementRewards(
                    experience = 500,
                ),
                sends_telemetry_event = true,
            )
        ).doneEvent {
            player.sendMessage(
                Component.text().text("Cool du bist fertig mit ").append(this.advancement.display!!.title())
            )
        }.criterionGrantEvent {
            println("Only Cool... $criterion")
        }

        /*
                val entity = Bukkit.getWorld("world")!!.spawnEntity(
                    Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0), EntityType.TEXT_DISPLAY
                ) as TextDisplay
        
                entity.text(MiniMessage.miniMessage().deserialize("<red>Hello</red>"))
        
                entity.playerInteractEntityEvent(null, null) {
                    Log.debug("Cool")
                    player.sendMessage(entity.text())
                }                
         */
    }

    override fun onDisable() {
        
    }
}