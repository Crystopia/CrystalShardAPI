package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.custom.advancements.Advancement
import net.crystopia.crystalshard.paper.custom.advancements.models.AdvancementModel
import net.crystopia.crystalshard.paper.custom.advancements.models.criteria.AdvancementCriteria
import net.crystopia.crystalshard.paper.custom.advancements.models.criteria.CriteriaTrigger
import net.crystopia.crystalshard.paper.custom.advancements.models.display.AdvancementDisplayIcon
import net.crystopia.crystalshard.paper.custom.advancements.models.rewards.AdvancementRewards
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.crystopia.crystalshard.tests.paper.models.PlayerKilledEntity
import net.crystopia.crystalshard.tests.paper.models.PlayerKilledEntityEntity
import net.crystopia.crystalshard.tests.paper.models.PlayerKilledEntityPlayer
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
            val adv = Advancement(
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
        }
    }

}