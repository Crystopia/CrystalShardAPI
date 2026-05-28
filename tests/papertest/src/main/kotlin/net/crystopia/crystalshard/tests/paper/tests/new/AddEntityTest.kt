package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.custom.advancements.advancement
import net.crystopia.crystalshard.paper.custom.advancements.models.AdvancementModel
import net.crystopia.crystalshard.paper.custom.advancements.models.criteria.AdvancementCriteria
import net.crystopia.crystalshard.paper.custom.advancements.models.criteria.CriteriaTrigger
import net.crystopia.crystalshard.paper.custom.advancements.models.display.AdvancementDisplay
import net.crystopia.crystalshard.paper.custom.advancements.models.display.AdvancementDisplayIcon
import net.crystopia.crystalshard.paper.custom.advancements.models.rewards.AdvancementRewards
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.addEntity
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
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.util.UUID

class AddEntityTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            val players = mutableListOf(player)

            val spawnLoc = player.location.clone().add(
                player.location.direction.multiply(3)
            )

            ClientPacketFactory.addEntity(
                entityId = 9999,
                entityUUID = UUID.randomUUID(),
                location = spawnLoc,
                entityType = EntityType.ZOMBIE,
                data = 0,
                yHeadRot = player.location.yaw.toDouble()
            ) {
                it.send(players)
            }

            println("AddEntity OK → ${spawnLoc.x}, ${spawnLoc.y}, ${spawnLoc.z}")
        }
    }
}