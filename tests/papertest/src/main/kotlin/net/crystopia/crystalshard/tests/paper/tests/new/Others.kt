package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.ServerPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.dhl.shared.data.entities.PositionMoveRotation
import net.crystopia.crystalshard.dhl.shared.data.maps.MapPatch
import net.crystopia.crystalshard.dhl.shared.data.world.Vec3
import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityDataSerializerType
import net.crystopia.crystalshard.dhl.shared.enums.entities.LookAnchor
import net.crystopia.crystalshard.dhl.shared.enums.scoreboard.*
import net.crystopia.crystalshard.dhl.shared.enums.teams.CollisionRule
import net.crystopia.crystalshard.dhl.shared.enums.teams.NameTagVisibility
import net.crystopia.crystalshard.dhl.shared.enums.teams.TeamAction
import net.crystopia.crystalshard.dhl.shared.enums.teams.TeamFlags
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.*
import net.crystopia.crystalshard.paper.dhl.packets.server.acceptTeleportationEvent
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.BlankFormatData
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.DisplayData
import net.crystopia.crystalshard.paper.dhl.types.scoreboard.ScoreData
import net.crystopia.crystalshard.paper.dhl.types.teams.Team
import net.crystopia.crystalshard.paper.dhl.types.world.WorldBorder
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class SendEntityEventTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            // Status 3 = Player death animation
            ClientPacketFactory.sendEntityEvent(
                entity = player,
                status = 47,
                world = player.world
            ) { it.send(mutableListOf(player)) }
            println("SendEntityEvent OK → Death animation")
        }
    }
}

class SendObjectiveUpdateTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.sendObjectiveUpdate(
                mode = ScoreBoardMode.CREATE,
                displaySlot = DisplaySlot.SIDEBAR,
                displayData = DisplayData(
                    name = "test_obj",
                    displayName = Component.text("Test"),
                    displayAutoUpdate = false,
                    numberFormat = NumberFormat.BLANK,
                    format = BlankFormatData(),
                    renderType = RenderType.INTEGER,
                    criteria = ObjectiveCriteria.DUMMY
                )
            ) { it.send(mutableListOf(player)) }
            println("SendObjectiveUpdate OK")
        }
    }
}

class SendPlayerCombatKillTest(name: String, sender: CommandSender, args: CommandArguments) :
    Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.sendPlayerCombatKill(
                entityId = player.entityId,
                message = Component.text("${player.name} wurde von einem Test besiegt")
            ) { it.send(mutableListOf(player)) }
            println("SendPlayerCombatKill OK")
        }
    }
}

class SendTeamTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.sendTeam(
                action = TeamAction.ADD,
                team = Team(
                    name = "test_team",
                    teamDisplayName = Component.text("Test Team"),
                    friendlyFlags = mutableListOf(TeamFlags.ALLOW_FRIENDLY_FIRE),
                    nameTagVisibility = NameTagVisibility.NEVER,
                    collisionRule = CollisionRule.ALWAYS,
                    teamColor = 'B',
                    teamPrefix = Component.text("[T] "),
                    teamSuffix = Component.empty(),
                    members = mutableListOf(player.name)
                )
            ) { it.send(mutableListOf(player)) }
            println("SendTeam OK")
        }
    }
}

class SendWorldEventTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            val loc = player.location
            // 1004 = Feuerball-Sound
            ClientPacketFactory.sendWorldEvent(
                type = 1004,
                pos = BlockPos(loc.blockX, loc.blockY, loc.blockZ),
                data = 0,
                globalEvent = false
            ) { it.send(mutableListOf(player)) }
            println("SendWorldEvent OK")
        }
    }
}

class SetContainerContentTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setContainerContent(
                id = 0,
                stateId = 1,
                items = mutableMapOf(
                    0 to ItemStack(Material.DIAMOND),
                    1 to ItemStack(Material.GOLD_INGOT),
                    8 to ItemStack(Material.EMERALD),
                ),
                carriedItem = null
            ) { it.send(mutableListOf(player)) }
            println("SetContainerContent OK")
        }
    }
}

class SetContainerDataTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            // Property 0 = Furnace fire icon
            ClientPacketFactory.setContainerData(
                id = 0,
                property = 0,
                value = 200
            ) { it.send(mutableListOf(player)) }
            println("SetContainerData OK")
        }
    }
}

class SetContainerSlotTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setContainerSlot(
                id = 0,
                revision = 1,
                slot = 4,
                item = ItemStack(Material.NETHER_STAR)
            ) { it.send(mutableListOf(player)) }
            println("SetContainerSlot OK → Nether Star in Slot 4")
        }
    }
}

class SetDefaultSpawnPositionTest(name: String, sender: CommandSender, args: CommandArguments) :
    Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            val loc = player.location
            ClientPacketFactory.setDefaultSpawnPosition(
                world = player.world,
                pos = BlockPos(loc.blockX, loc.blockY, loc.blockZ),
                yaw = loc.yaw,
                pitch = loc.pitch,
                angle = null
            ) { it.send(mutableListOf(player)) }
            println("SetDefaultSpawnPosition OK")
        }
    }
}

class SetDisplayObjectiveTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setDisplayObjective(
                displaySlot = DisplaySlot.SIDEBAR,
                displayData = DisplayData(
                    name = "test_display",
                    displayName = Component.text("§aTest Scoreboard"),
                    displayAutoUpdate = true,
                    numberFormat = NumberFormat.BLANK,
                    format = BlankFormatData(),
                    renderType = RenderType.INTEGER,
                    criteria = ObjectiveCriteria.DUMMY
                )
            ) { it.send(mutableListOf(player)) }
            println("SetDisplayObjective OK")
        }
    }
}

class SetEntityDataTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            // Index 0, Byte: Flags (0x20 = invisible)
            ClientPacketFactory.setEntityData(
                entity = player,
                entityData = mutableListOf(
                    EntityMetadata(
                        index = 0,
                        type = EntityDataSerializerType.BYTE,
                        value = 0x20.toByte()
                    )
                )
            ) { it.send(mutableListOf(player)) }
            println("SetEntityData OK → Spieler client-seitig unsichtbar")
        }
    }
}

class SetHealthTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setHealth(
                health = 10f,
                food = 20,
                saturation = 5f
            ) { it.send(mutableListOf(player)) }
            println("SetHealth OK → Client zeigt 5 Herzen")
        }
    }
}

class SetItemOnCursorTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setItemOnCursor(
                item = ItemStack(Material.DIAMOND),
                slot = 0
            ) { it.send(mutableListOf(player)) }
            println("SetItemOnCursor OK")
        }
    }
}

class SetMapItemDataTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            // Leerer Farbpatch: 128x128 weiße Map
            val colors = ByteArray(128 * 128) { 0 }
            ClientPacketFactory.setMapItemData(
                mapId = 1,
                scale = 0,
                locked = false,
                decorations = mutableListOf(),
                colorPatch = MapPatch(
                    startX = 0,
                    startZ = 0,
                    width = 128,
                    height = 128,
                    mapColors = colors.toMutableList()
                )
            ) { it.send(mutableListOf(player)) }
            println("SetMapItemData OK")
        }
    }
}

class SetMerchantOfferTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setMerchantOffer(
                windowId = 1,
                merchantOffers = net.crystopia.crystalshard.paper.dhl.types.merchant.MerchantOffers(
                    offers = mutableListOf(
                        net.crystopia.crystalshard.paper.dhl.types.merchant.MerchantOffer(
                            baseCost = net.crystopia.crystalshard.paper.dhl.types.merchant.ItemCost(
                                itemStack = ItemStack(Material.EMERALD),
                                count = 5
                            ),
                            optionalCost = null,
                            result = ItemStack(Material.DIAMOND),
                            uses = 0,
                            maxUses = 10,
                            experience = 5,
                            priceMultiplier = 0.05f,
                            demand = 0,
                            specialPrice = 0,
                            ignoreDiscounts = false,
                            experienceReward = true
                        )
                    )
                ),
                levelProgress = 1,
                experience = 0,
                leveled = true,
                refreshable = false
            ) { it.send(mutableListOf(player)) }
            println("SetMerchantOffer OK")
        }
    }
}

class SetPassengersTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            // Fake-Entity muss vorher gespawnt sein (z.B. via AddEntity)
            // Hier nur als Beispiel mit leerem Passagier-Array
            ClientPacketFactory.setPassengers(
                entity = player,
                passengers = mutableListOf()
            ) { it.send(mutableListOf(player)) }
            println("SetPassengers OK")
        }
    }
}

class SetScoreInDisplayObjectTest(name: String, sender: CommandSender, args: CommandArguments) :
    Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setScoreInDisplayObject(
                score = ScoreData(
                    displayId = "test_display",
                    ownerName = player.name,
                    score = 42,
                    displayName = Component.text("42 Punkte"),
                    numberFormat = NumberFormat.BLANK,
                    format = BlankFormatData()
                )
            ) { it.send(mutableListOf(player)) }
            println("SetScoreInDisplayObject OK")
        }
    }
}

class ResetScoreInDisplayObjectTest(name: String, sender: CommandSender, args: CommandArguments) :
    Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.resetScoreInDisplayObject(
                score = ScoreData(
                    displayId = "test_display",
                    ownerName = player.name,
                    score = 0,
                    displayName = Component.empty(),
                    numberFormat = NumberFormat.BLANK,
                    format = BlankFormatData()
                )
            ) { it.send(mutableListOf(player)) }
            println("ResetScoreInDisplayObject OK")
        }
    }
}

class SetTabListTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setTabList(
                header = Component.text("§6=== Test Server ==="),
                footer = Component.text("§7Packet Test läuft")
            ) { it.send(mutableListOf(player)) }
            println("SetTabList OK")
        }
    }
}

class SetTimeTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            // 6000 = Mittag
            ClientPacketFactory.setTime(
                gameTime = player.world.fullTime,
                dayTime = 6000L,
                tickDayTime = false
            ) { it.send(mutableListOf(player)) }
            println("SetTime OK → Client zeigt Mittag")
        }
    }
}

class SetWorldBorderCenterTest(name: String, sender: CommandSender, args: CommandArguments) :
    Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setWorldBorderCenter(
                border = WorldBorder(
                    world = player.world,
                    size = 1000.0,
                    centerX = 0.0,
                    centerZ = 0.0,
                    absoluteMaxSize = 29999984,
                    damagePerBlock = 0.2,
                    safeZone = 5.0,
                    warningBlocks = 5,
                    warningTime = 15,
                    oldLerpSize = 1000.0,
                    newLerpSize = 900.0,
                    lerpTime = 200L,
                    lerpTimeStart = System.currentTimeMillis(),
                )
            ) { it.send(mutableListOf(player)) }
            println("SetWorldBorderCenter OK")
        }
    }
}

class SetWorldBorderLerpSizeTest(name: String, sender: CommandSender, args: CommandArguments) :
    Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setWorldBorderLerpSize(
                border = WorldBorder(
                    world = player.world,
                    size = 1000.0,
                    centerX = 0.0,
                    centerZ = 0.0,
                    absoluteMaxSize = 29999984,
                    damagePerBlock = 0.2,
                    safeZone = 5.0,
                    warningBlocks = 5,
                    warningTime = 15,
                    oldLerpSize = 1000.0,
                    newLerpSize = 900.0,
                    lerpTime = 200L,
                    lerpTimeStart = System.currentTimeMillis(),
                )
            ) { it.send(mutableListOf(player)) }
            println("SetWorldBorderLerpSize OK")
        }
    }
}

class SetWorldBorderSizeTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setWorldBorderSize(
                border = WorldBorder(
                    world = player.world,
                    size = 1000.0,
                    centerX = 0.0,
                    centerZ = 0.0,
                    absoluteMaxSize = 29999984,
                    damagePerBlock = 0.2,
                    safeZone = 5.0,
                    warningBlocks = 5,
                    warningTime = 15,
                    oldLerpSize = 1000.0,
                    newLerpSize = 900.0,
                    lerpTime = 200L,
                    lerpTimeStart = System.currentTimeMillis(),
                )
            ) { it.send(mutableListOf(player)) }
            println("SetWorldBorderSize OK → Border auf 500 gesetzt")
        }
    }
}

class SetWorldBorderWarningDelayTest(name: String, sender: CommandSender, args: CommandArguments) :
    Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setWorldBorderWarningDelay(
                border = WorldBorder(
                    world = player.world,
                    size = 1000.0,
                    centerX = 0.0,
                    centerZ = 0.0,
                    absoluteMaxSize = 29999984,
                    damagePerBlock = 0.2,
                    safeZone = 5.0,
                    warningBlocks = 5,
                    warningTime = 15,
                    oldLerpSize = 1000.0,
                    newLerpSize = 900.0,
                    lerpTime = 200L,
                    lerpTimeStart = System.currentTimeMillis(),
                )
            ) { it.send(mutableListOf(player)) }
            println("SetWorldBorderWarningDelay OK")
        }
    }
}

class SetWorldBorderWarningDistanceTest(name: String, sender: CommandSender, args: CommandArguments) :
    Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.setWorldBorderWarningDistance(
                border = WorldBorder(
                    world = player.world,
                    size = 1000.0,
                    centerX = 0.0,
                    centerZ = 0.0,
                    absoluteMaxSize = 29999984,
                    damagePerBlock = 0.2,
                    safeZone = 5.0,
                    warningBlocks = 5,
                    warningTime = 15,
                    oldLerpSize = 1000.0,
                    newLerpSize = 900.0,
                    lerpTime = 200L,
                    lerpTimeStart = System.currentTimeMillis(),
                )
            ) { it.send(mutableListOf(player)) }
            println("SetWorldBorderWarningDistance OK")
        }
    }
}

class TakeItemTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.takeItem(
                itemId = 1,
                player = player,
                amount = 1
            ) { it.send(mutableListOf(player)) }
            println("TakeItem OK")
        }
    }
}

class TeleportEntityTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            val targetLoc = Location(Bukkit.getWorld("world_the_end"), 0.0, 0.0, 0.0)
            ClientPacketFactory.teleportEntity(
                entity = player,
                location = targetLoc,
                onGround = false
            ) { it.send(mutableListOf(player)) }
            println("TeleportEntity OK")
        }
    }
}

object UpdateAttributesTest : Test("AttributesTest") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.updateAttributes(
                entity = player,
                attributes = mutableListOf(
                    net.crystopia.crystalshard.paper.dhl.types.attributes.Attribute(
                        id = Attribute.SCALE,
                        value = 40.0,
                        modifiers = mutableListOf()
                    )
                )
            ) { it.send(mutableListOf(player)) }
        }
    }
}

class UpdateEntityPositionSyncTest(name: String, sender: CommandSender, args: CommandArguments) :
    Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            val loc = player.location
            ClientPacketFactory.updateEntityPositionSync(
                entity = player,
                values = PositionMoveRotation(
                    position = Vec3(0.0, 0.0, 0.0),
                    deltaMovement = Vec3(0.0, 0.0, 0.0),
                    yRot = loc.yaw,
                    xRot = loc.pitch
                ),
                onGround = false
            ) { it.send(mutableListOf(player)) }
            println("UpdateEntityPositionSync OK")
        }
    }
}

class UpdatePlayerLookAtTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            val loc = player.location
            ClientPacketFactory.updatePlayerLookAt(
                entity = player,
                fromAnchor = LookAnchor.EYES,
                toAnchor = LookAnchor.FEET,
                x = loc.x,
                y = loc.y + 10,
                z = loc.z
            ) { it.send(mutableListOf(player)) }
            println("UpdatePlayerLookAt OK")
        }
    }
}

class UpdatePlayerPositionTest(name: String, sender: CommandSender, args: CommandArguments) :
    Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            val loc = player.location
            val teleportId = 42

            ServerPacketFactory.acceptTeleportationEvent(
                player,
                NamespacedKey("position_test", "teleport"),
                false
            ) { id ->
                if (id == teleportId) println("UpdatePlayerPosition Teleport bestätigt OK")
            }

            ClientPacketFactory.updatePlayerPosition(
                change = PositionMoveRotation(
                    position = Vec3(0.0, 0.0, 0.0),
                    deltaMovement = Vec3(0.0, 0.0, 0.0),
                    yRot = loc.yaw,
                    xRot = loc.pitch
                ),
                teleportId = teleportId,
                relatives = mutableSetOf()
            ) { it.send(mutableListOf(player)) }
            println("UpdatePlayerPosition OK")
        }
    }
}

class UpdatePlayerRotationTest(name: String, sender: CommandSender, args: CommandArguments) :
    Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.updatePlayerRotation(
                yRot = 180f,
                relativeY = false,
                xRot = 0f,
                relativeX = false
            ) { it.send(mutableListOf(player)) }
            println("UpdatePlayerRotation OK → Spieler schaut nach Süden")
        }
    }
}

class UpdateTickingStateTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.updateTickingState(
                tickRate = 5f,
                isFrozen = true
            ) { it.send(mutableListOf(player)) }
            println("UpdateTickingState OK → TPS client-seitig auf 5 gesetzt")
        }
    }
}

class UpdateTickingStepTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.updateTickingStep(
                tickSteps = 20
            ) { it.send(mutableListOf(player)) }
            println("UpdateTickingStep OK")
        }
    }
}
