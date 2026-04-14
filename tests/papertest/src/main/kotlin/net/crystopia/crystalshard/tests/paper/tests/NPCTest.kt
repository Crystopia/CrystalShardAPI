package net.crystopia.crystalshard.tests.paper.tests

import com.destroystokyo.paper.profile.ProfileProperty
import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.MINI_MESSAGE
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.ServerPacketFactory
import net.crystopia.crystalshard.paper.dhl.packets.client.addEntity
import net.crystopia.crystalshard.paper.dhl.packets.client.createEquipment
import net.crystopia.crystalshard.paper.dhl.packets.client.playerInfoUpdate
import net.crystopia.crystalshard.paper.dhl.packets.client.setEntityData
import net.crystopia.crystalshard.paper.dhl.packets.client.setPassengers
import net.crystopia.crystalshard.paper.dhl.packets.client.teleportEntity
import net.crystopia.crystalshard.paper.dhl.packets.server.interactEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType
import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.EquipmentSlot
import net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.paper.simulacrum.SimulacrumFactory
import net.crystopia.crystalshard.paper.simulacrum.npc.Npc
import net.crystopia.crystalshard.tests.paper.CrystalShardPluginTest
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class NPCTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            SimulacrumFactory.createNpc<Npc>(
                Location(CrystalShardPluginTest.instance.server.worlds.first(), 0.0, 0.0, 0.0),
                NamespacedKey("test", "test"), "test"
            ) {

                playerEntity.playerListName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))
                (sender as Player).playerListName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))
                playerEntity.displayName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))
                (sender as Player).displayName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))
                playerEntity.customName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))
                (sender as Player).customName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))

                playerEntity.isCustomNameVisible = true
                (sender as Player).isCustomNameVisible = true

                val actions = mutableListOf<InfoUpdateAction>()
                actions.add(InfoUpdateAction.ADD_PLAYER)
                actions.add(InfoUpdateAction.UPDATE_DISPLAY_NAME)
                actions.add(InfoUpdateAction.UPDATE_LISTED)

                val playerProfile = playerEntity.playerProfile

                playerProfile.setProperty(
                    ProfileProperty(
                        "textures",
                        "ewogICJ0aW1lc3RhbXAiIDogMTc0MjA2NTk1NzMxOCwKICAicHJvZmlsZUlkIiA6ICIzYjBmNTM5MmRlNzM0YmZjYmJkOTMxYzMxYmFkODMxMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJjYXRhbmRCIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxNjNhNjNiMDliNTE4MzY4YmU0ZDhlYTA5ODg0NTM4ZGUxNTRlYjU0Y2ZlYjA4ZjA5NmE2Y2Y0NDdjZWZkMzUiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==",
                        "ElfmMCz1IPrR4+PdwXRk0AbV5CO3/jrSU4ciTq8DdrYgQW1KMM3OVYyz8dig6P4dWaCaQpOUMMEdyNuqaTKCqO4Jjznk95DTziOlCU0aPVjRCdWElE0oU3xP3nGYIWw/O/jUPD1z+0bkFE0l4gwEL+QBKYjV/tOTNZAWuNuOSRc/lnrpxFPmpVCypq5Mk/e34ZAcQFZPn+MjTrLyBTJfl7A6PrKEKg5zXBIRGOYZ8qCfJtLZOzJPmKF9gtTUmFM7WzmrJTD8+dD66JEbeoSIMOdw0AC8EMm9HL2Ahmd8/1NqtT9WlHvQGb8ItL/JtuygusnA+o5bVnzXLK4i+DXy5dojlJOMNKJJg8AULhrBQNH2ZUGIvJ9mR8re6HFOVqiRtJfXoVYhzJR0PFekb9JgCH0ZKBewPtYjHhviSscxd735c0BeVVli0AqB0POcMkGkEefYTFcVMxKSC2epKbwTqn5vvxF5j18cYBgyUzxIMF1QNH06CAH4Hj60LUbBfcJdtq2A43xmrXRNb8Hp/+9t4tXtyoUWnFfDSjS4vem6JRZ5qaMHzxfeD4e0ejqG7D7zscDEA5+SYTqOazLJPjfbucTVkeyH1Ec6kUHJmoOcf7lmryCHVilZQVLyWEpV0a7aPf9AOzB/tjJSKYzpovWlybF9X3MDNOUPKaGoSyYTj6I="
                    )
                )
                playerEntity.playerProfile = playerProfile

                ClientPacketFactory.playerInfoUpdate(
                    playerEntity,
                    actions,
                ) { packet ->
                    packet.send(mutableListOf((sender as Player)))
                }

                ClientPacketFactory.addEntity(
                    entityId = playerEntity.entityId,
                    entityUUID = playerEntity.uniqueId,
                    location = playerEntity.location,
                    entityType = EntityType.PLAYER,
                    data = 0,
                    yHeadRot = 0.0,
                ) { packet ->
                    packet.send(mutableListOf((sender as Player)))
                }

                val equipmentList: MutableList<Pair<EquipmentSlot, ItemStack>> = mutableListOf()
                equipmentList.add(
                    Pair(
                        EquipmentSlot.MAINHAND, ItemStack(Material.STONE_SHOVEL)
                    )
                )

                ClientPacketFactory.createEquipment(
                    playerEntity.entityId, equipmentList
                ) { packet ->
                    packet.send(mutableListOf((sender as Player)))
                }


                ClientPacketFactory.setEntityData(
                    playerEntity.entityId, mutableListOf(
                        EntityMetadata<Byte>(
                            index = 16,
                            type = EntityDataSerializerType.DATA_PLAYER_MODE_CUSTOMISATION,
                            value = (0x01 or 0x02 or 0x04 or 0x08 or 0x10 or 0x20 or 0x40).toByte()
                        )
                    )
                ) { packet ->
                    packet.send(mutableListOf((sender as Player)))
                }


                ClientPacketFactory.teleportEntity(
                    playerEntity.entityId,
                    Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0),
                    false
                ) { packet ->
                    packet.send(mutableListOf((sender as Player)))
                }


                ServerPacketFactory.interactEvent(
                    Shard_ServerPacketData(
                        player = (sender as Player),
                        name = NamespacedKey(CrystalShardPluginTest.instance, "testnpcinteraction"),
                        plugin = CrystalShardPluginTest.instance,
                        shouldPublish = true
                    )
                ) {
                    (sender as Player).sendMessage("COOL")

                    val fakeDisplay = SimulacrumFactory.createEntityInstance<ItemDisplay>(
                        EntityType.ITEM_DISPLAY,
                        Location(Bukkit.getWorld("world"), 1.0, 1.0, 1.0)
                    ) {

                        ClientPacketFactory.addEntity(
                            this.entityId,
                            this.uniqueId,
                            location,
                            EntityType.ITEM_DISPLAY,
                            0,
                            0.0
                        ) { packet ->
                            packet.send(mutableListOf((sender as Player)))
                        }
                    }

                    ClientPacketFactory.setPassengers(fakeDisplay, mutableListOf(playerEntity)) { packet ->
                        packet.send(mutableListOf((sender as Player)))
                    }
                }


            }
        }
    }

}