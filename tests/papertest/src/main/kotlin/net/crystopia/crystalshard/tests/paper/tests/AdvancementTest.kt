package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.custom.advancements.advancement
import net.crystopia.crystalshard.paper.custom.advancements.models.AdvancementModel
import net.crystopia.crystalshard.paper.custom.advancements.models.criteria.AdvancementCriteria
import net.crystopia.crystalshard.paper.custom.advancements.models.criteria.CriteriaTrigger
import net.crystopia.crystalshard.paper.custom.advancements.models.display.AdvancementDisplayIcon
import net.crystopia.crystalshard.paper.custom.advancements.models.rewards.AdvancementRewards
import net.crystopia.crystalshard.tests.paper.CrystalShardPluginTest
import net.crystopia.crystalshard.tests.paper.models.PlayerKilledEntity
import net.crystopia.crystalshard.tests.paper.models.PlayerKilledEntityEntity
import net.crystopia.crystalshard.tests.paper.models.PlayerKilledEntityPlayer
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender

class AdvancementTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {

    override fun command() {
        test {

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

            advancement(NamespacedKey(CrystalShardPluginTest.instance, "advancement")) {
                advancementData = AdvancementModel(
                    display = net.crystopia.crystalshard.paper.custom.advancements.models.display.AdvancementDisplay(
                        icon = AdvancementDisplayIcon(
                            id = Material.COMPASS.key.toString(),
                            count = 1,
                            // component = ATTRIBUTE...
                        ),
                        title = Json.encodeToJsonElement(
                            JSONComponentSerializer.builder().build().serialize(Component.text("Cool"))
                        ),
                        description = Json.encodeToJsonElement(
                            JSONComponentSerializer.builder().build().serialize(Component.text("Cool"))
                        ),
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
                load()
                doneEvent {
                    player.sendMessage(
                        Component.text().text("Cool du bist fertig mit ").append(this.advancement.display!!.title())
                    )
                }
                criterionGrantEvent {
                    println("Only Cool... $criterion")
                }
            }
        }
    }

}