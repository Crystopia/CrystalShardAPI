package net.crystopia.crystalshardtest

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import gg.flyte.twilight.twilight
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import net.crystopia.crystalshard.paper.CrystalShard
import net.crystopia.crystalshard.paper.custom.CrystalEvents
import net.crystopia.crystalshard.paper.extension.text
import net.crystopia.crystalshard.extras.advancements.Advancement
import net.crystopia.crystalshard.extras.advancements.models.AdvancementModel
import net.crystopia.crystalshard.extras.advancements.models.criteria.AdvancementCriteria
import net.crystopia.crystalshard.extras.advancements.models.criteria.CriteriaTrigger
import net.crystopia.crystalshard.extras.advancements.models.display.AdvancementDisplay
import net.crystopia.crystalshard.extras.advancements.models.display.AdvancementDisplayIcon
import net.crystopia.crystalshard.extras.advancements.models.rewards.AdvancementRewards
import net.crystopia.crystalshardtest.events.PlayerJoin
import net.crystopia.crystalshardtest.models.PlayerKilledEntity
import net.crystopia.crystalshardtest.models.PlayerKilledEntityEntity
import net.crystopia.crystalshardtest.models.PlayerKilledEntityPlayer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    companion object {
        lateinit var instance: Main
    }

    init {
        instance = this
    }
    
    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIPaperConfig(this).silentLogs(true))
        CrystalShard.init(this)
        
    }

    lateinit var adv: Advancement
    
    override fun onEnable() {
        CommandAPI.onEnable();
        val twilight = twilight(this)

        TestyCommand
        server.pluginManager.registerEvents(PlayerJoin, this)
        server.pluginManager.registerEvents(CrystalEvents, this)


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
                display = AdvancementDisplay(
                    icon = AdvancementDisplayIcon(
                        id = Material.COMPASS.key.toString(),
                        count = 1,
                        // component = JSONComponentSerializer.builder().build().serialize(Component.text("Cool"))
                    ),
                    title = JSONComponentSerializer.builder().build().serialize(Component.text("Cool")),
                    description = JSONComponentSerializer.builder().build().serialize(Component.text("Cool")),
                    frame = io.papermc.paper.advancement.AdvancementDisplay.Frame.TASK,
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