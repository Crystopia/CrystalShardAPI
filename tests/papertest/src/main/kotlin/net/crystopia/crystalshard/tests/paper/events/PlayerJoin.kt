package net.crystopia.crystalshard.tests.paper.events

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import gg.flyte.twilight.gui.GUI.Companion.openInventory
import gg.flyte.twilight.gui.gui
import net.crystopia.crystalshard.common.extension.MINI_MESSAGE
import net.crystopia.crystalshard.common.extension.copyToClipboard
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.box.GUI
import net.crystopia.crystalshard.paper.dhl.shared.enums.gui.MenuType
import net.crystopia.crystalshard.paper.pack.font.toGuiRow
import net.crystopia.crystalshard.tests.paper.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.entity.TextDisplay
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*

object PlayerJoin : Listener {

    lateinit var display: TextDisplay

    var currentPage = 1
    fun title(page: Int) = Component.text().toGuiRow(1, null) {
        text("Current Page: $page/4")
        copyToClipboard("Cool text")
    }.toGuiRow(6, null) {
        text("Cool").build()
    }.build()

    fun basicGui(page: Int) = gui(
        title(page), 54
    ) {
        set(9, ItemStack(Material.ARROW).apply {
            val itemMeta = itemMeta
            itemMeta.isHideTooltip = true
            this.itemMeta = itemMeta
        }) {
            isCancelled = true

            if (page > 1) currentPage -= 1
            else viewer.sendMessage(MiniMessage.miniMessage().deserialize("<red>You are at page one</red>"))

            println(title)

            reopenInv(viewer)
        }
        set(16, ItemStack(Material.ARROW).apply {
            val itemMeta = itemMeta
            itemMeta.isHideTooltip = true
            this.itemMeta = itemMeta
        }) {
            isCancelled = true

            if (page < 4) currentPage += 1
            else viewer.sendMessage(MiniMessage.miniMessage().deserialize("<red>No more pages</red>"))

            reopenInv(viewer)
        }
    }

    var inv = Bukkit.createInventory(null, InventoryType.CHEST, MiniMessage.miniMessage().deserialize("Cool"))

    fun reopenInv(player: Player) {
        player.openInventory(basicGui(currentPage))
    }

    val checked = mutableListOf<UUID>()

    @EventHandler
    fun onMove(event: PlayerQuitEvent) {
        checked.remove(event.player.uniqueId)
    }

    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        return

        /*
         ClientPacketFactory.applyCooldown(
             Material.PAPER, 1000
         ) { packet ->
             packet.send(mutableListOf(event.player))
         }
         */


        /*
        if (!checked.contains(event.player.uniqueId)) {
            checked.add(event.player.uniqueId)
            event.player.clientMods(
                Main.instance
            ) {
                onMod("text.skinlayers.title") {
                    check { hasMod ->

                        println(hasMod)

                        if (hasMod)
                            disconnect()
                    }
                }
            }
        }
         */

    }

    var player: Player? = null
    var waypointUUID = UUID.randomUUID()

    val savedUUID = UUID.randomUUID().toString()

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {

        /*
        ClientPacketFactory.showDialog(
            ConfirmationDialog(
                common = CommonDialogData(
                    title = Component.text("PACKET DIALOG"),
                    externalTitle = null,
                    canCloseWithEscape = false,
                    pause = false,
                    afterAction = DialogAction.CLOSE,
                    body = mutableListOf(
                        DialogBodyPlainMessage(
                            contents = Component.text("Hey this is a dialog only for you!"),
                            width = 100
                        )
                    ),
                    inputs = mutableListOf()
                ),
                yesButton = ActionButton(
                    button = CommonButtonData(
                        label = Component.text("Click me for more packets :)"),
                        tooltip = null,
                        width = 500
                    ),
                    action = ActionCustomAll(
                        id = NamespacedKey("testy", "packet"),
                        additionsNBT = mutableMapOf(Pair("test","1234"))
                    )
                ),
                noButton = ActionButton(
                    button = CommonButtonData(
                        label = Component.text("No Packets... :("),
                        tooltip = null,
                        width = 100
                    ),
                    action = ActionStaticAction(
                        event = ClickEvent.openUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                    )
                )
            )
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }
         */

        /*
        ClientPacketFactory.applyMobEffect(
            event.player.entityId,
            EffectInstance(
                type = EffectType.HASTE,
                duration = 5000,
                amplifier = 3,
                ambient = false,
                visible = true,
                showIcon = true
            ),
            true
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }
         */


        /*
        ClientPacketFactory.sendTeam(
            action = TeamAction.ADD,
            team = Team(
                name = "testteam",
                teamDisplayName = Component.text().text("<pink>TEAM MEMBER</pink>").build(),
                friendlyFlags = mutableListOf(),
                nameTagVisibility = NameTagVisibility.ALWAYS,
                collisionRule = CollisionRule.ALWAYS,
                teamColor = 'A',
                teamPrefix = Component.text("DUMM "),
                teamSuffix = Component.text(" AM DÜMMSTEN"),
                members = mutableListOf(event.player.name)
            )
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }
        ClientPacketFactory.sendTeam(
            action = TeamAction.ADD,
            team = Team(
                name = "xdvsdfsdfsfd",
                teamDisplayName = Component.text().text("<pink>TEAM MEMBER</pink>").build(),
                friendlyFlags = mutableListOf(),
                nameTagVisibility = NameTagVisibility.ALWAYS,
                collisionRule = CollisionRule.NEVER,
                teamColor = 'A',
                teamPrefix = Component.text("NPC", NamedTextColor.RED),
                teamSuffix = Component.text(""),
                members = mutableListOf(player!!.name)
            )
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }
         */

        /*
        org.bukkit.Particle.NOTE.builder()
            .location(Location(Bukkit.getWorld("world_nether")!!, 2.0, 2.0, 2.0))
            .offset(2.0, 0.2, 2.0)
            .count(14)
            .receivers(23, false)
            .spawn();
         */


        // VibrationParticleOption(
        //                    entityId = event.player.entityId,
        //                    offSet = 0.0F,
        //                    blockPos = null,
        //                    arrivalInTicks = 20
        //                )

        /*
        ClientPacketFactory.spawnParticle(
            particle = Particle(
                particle = ParticleType.NOTE,
                options = null,
                overrideLimiter = true,
                alwaysShow = false,
                x = 1.0,
                y = 1.0,
                z = 1.0,
                xOffSet = 2.0F,
                yOffSet = 2.0F,
                zOffSet = 2.0F,
                maxSpeed = 5F,
                count = 500
            )
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }
         */

        /*
        ClientPacketFactory.playRespawnPacket(
            world = Bukkit.getWorld("world_the_end")!!,
            deathLocation = Location(Bukkit.getWorld("world")!!, 0.0, 0.0, 0.0),
            gameMode = GameMode.CREATIVE,
            isDebug = false,
            isFlat = false,
            portalCooldown = 100000,
            datakept = 0x01,
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }
         */

        /*
        val worldBorder = WorldBorder(
            world = Bukkit.getWorld("world")!!,
            size = 50.0,
            centerX = 0.0,
            centerZ = 0.0,
            absoluteMaxSize = null,
            safeZone = 0.0,
            warningBlocks = 10,
            warningTime = 10,
            oldLerpSize = 50.0,
            newLerpSize = 5.0,
            lerpTime = 10000,
            damagePerBlock = 0.5
        )

        ClientPacketFactory.initWorldBorder(
            worldBorder
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }
        ClientPacketFactory.setWorldBorderLerpSize(
            worldBorder
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }

        Bukkit.getWorld("world")!!.worldBorder.damageAmount
         */
        /*
        ClientPacketFactory.runGameEvent(
            type = GameEventType.IMMEDIATE_RESPAWN,
            action = 1F
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }

        ClientPacketFactory.teleportEntityPacket(
            entityId = event.player.entityId,
            location = Location(Bukkit.getWorld("world_the_end")!!, 0.0, 0.0, 0.0),
            onGround = false
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }

        ClientPacketFactory.createAnimatePacket(
            player!!.entityId,
            0
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }

        ClientPacketFactory.setScoreInDisplayObject(
            ScoreData(
                displayId = "testy",
                ownerName = savedUUID,
                score = -1,
                displayName = Component.text().text(" EXTRA PACKET ").build(),
                numberFormat = NumberFormat.FIXED,
                format = FixedFormatData(
                    text = Component.text("")
                ),
            )
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }
         */
    }

    var guiDATA: GUI? = null

    @EventHandler
    fun onEvent(event: PlayerJumpEvent) {
        // ITEMS
        val item = ItemStack(Material.ARROW)
        val meta = item.itemMeta
        meta.displayName(MINI_MESSAGE.deserialize("<blue>CLICK ME FOR FREE VBUGS</blue>"))
        meta.persistentDataContainer.set(NamespacedKey("testy", "text"), PersistentDataType.STRING, "COOL")
        item.itemMeta = meta

        val item2 = ItemStack(Material.ARROW)
        val meta2 = item2.itemMeta
        meta2.displayName(MINI_MESSAGE.deserialize("<blue>CLICK MEadsad FOR FREE VBUGS</blue>"))
        meta2.persistentDataContainer.set(NamespacedKey("testy", "text"), PersistentDataType.STRING, "TTTTTTTTTTT")
        item2.itemMeta = meta2

        val items = mutableMapOf<Int, ItemStack>()
        items[0] = item
        items[1] = item2

        guiDATA = net.crystopia.crystalshard.paper.box.gui(
            Component.text("PacketGUI", NamedTextColor.BLUE),
            MenuType.ANVIL,
            false,
            Main.instance
        ) {
            players(event.player)
            open()
            slot(
                1,
                GUI.Slot(
                    item = item,
                    revision = 1,
                    cancel =  true
                )
            ) { button, click ->
                println("[ITEM] BUTTON $button")
                println("[ITEM] CLICK $click")
            }.slot(
                mutableListOf(2),
                GUI.Slot(
                    item = ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                    revision = 1
                )
            ) { button, click ->
                slot(
                    0, GUI.Slot(
                    item = ItemStack(Material.DIAMOND_BLOCK),
                    revision = 0
                )) { button, click ->

                }
            }
                .click {
                    println("CLICK $this")
                }.buttonClick {
                    println("BUTTON $this")
                }
        }

    }

    fun onJump(event: PlayerJumpEvent) {
        /*
        val result = ItemStack(Material.DIRT)
        val resultMeta = result.itemMeta
        resultMeta.displayName(Component.text().text("<gray>!REICHHEITS STATUS!</gray>").build())

        result.itemMeta = resultMeta

        val baseCost = ItemStack(Material.EMERALD_BLOCK)

        /*
        ClientPacketFactory.setMerchantOffer(
            windowId = 3243443,
            merchantOffers = MerchantOffers(
                offers = mutableListOf(

                    MerchantOffer(
                        baseCost = ItemCost(
                            itemStack = baseCost,
                            count = 5
                        ),
                        optionalCost = null,
                        result = result,
                        uses = 19,
                        maxUses = 20,
                        experience = 1,
                        priceMultiplier = 2F,
                        demand = 0,
                        specialPrice = 4,
                        ignoreDiscounts = false,
                        experienceReward = false
                    )

                )
            ),
            levelProgress = 1,
            experience = 3,
            leveled = true,
            refreshable = false
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }
         */

        val displayData = DisplayData(
            name = "testy",
            displayName = Component.text().text("<rainbow>PACKET BOARD</rainbow>").build(),
            displayAutoUpdate = false,
            numberFormat = NumberFormat.FIXED,
            format = FixedFormatData(
                text = Component.text("TEST")
            ),
            renderType = RenderType.INTEGER,
            criteria = ObjectiveCriteria.DUMMY
        )

        ClientPacketFactory.sendObjectiveUpdate(
            ScoreBoardMode.CREATE,
            DisplaySlot.SIDEBAR,
            displayData,
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }

        ClientPacketFactory.setScoreInDisplayObject(
            ScoreData(
                displayId = displayData.name,
                ownerName = UUID.randomUUID().toString(),
                score = 2,
                displayName = Component.text().text("<gray>Packet sent...</gray>").click {
                    this.sendMessage(Component.text("SECRET TEXT", NamedTextColor.GRAY))
                }.build(),
                numberFormat = NumberFormat.FIXED,
                format = FixedFormatData(
                    text = Component.text(" ")
                ),
            )
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }

        ClientPacketFactory.setScoreInDisplayObject(
            ScoreData(
                displayId = displayData.name,
                ownerName = savedUUID,
                score = 1,
                displayName = Component.text().text(" ").build(),
                numberFormat = NumberFormat.FIXED,
                format = FixedFormatData(
                    text = Component.text("")
                ),
            )
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }

        ClientPacketFactory.setScoreInDisplayObject(
            ScoreData(
                displayId = displayData.name,
                ownerName = savedUUID,
                score = 0,
                displayName = Component.text().text("<green><b>PACKET RECEIVED!</b></green>").build(),
                numberFormat = NumberFormat.FIXED,
                format = FixedFormatData(
                    text = Component.text(" ")
                ),
            )
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }


        ClientPacketFactory.setDisplayObjective(
            ScoreBoardMode.UPDATE,
            DisplaySlot.SIDEBAR,
            displayData,
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }


        /*
        Shard_ServerboundCustomClickActionPacket.attach(
            "sdfsdfsddfsd", Main.instance, event.player
        ) {

            println(key)
            println(this.payload.id)
            println(this.payload.type.name)
            println(this.payload.type.prettyName)
            println(this.payload.type.data)

        }
         */


        /*
        ClientPacketFactory.sendWaypoint(
            WaypointOperation.TRACK,
            TrackedWaypoint(
                identifier = waypointUUID,
                icon = WaypointIcon(
                    style = "default",
                    color = 0x60008000
                ),
                type = WaypointType.VEC3I,
                data = WaypointDataVec3i(
                    10, 10, 10
                )
            )
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }
         */

        // event.player.showDialog(dialog)

        ClientPacketFactory.teleportEntityPacket(
            player!!.entityId,
            Location(Bukkit.getWorld("world"), 1.0, 1.0, 1.0),
            false
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }





        /*
        Shard_ServerboundUseItemPacket.attach("sdsdf", Main.instance, event.player) {
            this.hand
            event.player.sendMessage(this.sequence.toString())
        }

        Shard_ServerboundUseItemOnPacket.attach("sdfsdfgfgdfgdfgdf", Main.instance, event.player) {
            this.hand
            event.player.sendMessage(this.hitResult.type.toString())
        }

        Shard_ServerboundSelectTradePacket.attach("sdgdfgdgdgdsfgdfgfdgdfgdfgdfg", Main.instance, event.player) {
            event.player.sendMessage(this.item.toString())
        }
         */
         */
    }

    @EventHandler
    fun addNPCsOnJoin(event: PlayerJoinEvent) {
        /*
                if (event.player.name != "xyzjesper") return
                Main.instance.adv.complete(event.player) {

                }
                val message = Component.text("Dein Code: ", NamedTextColor.GRAY).append(
                    Component.text().text("ABC-123").clickEvent(ClickEvent.callback { audience ->
                        audience.sendMessage(Component.text("COOl"))
                    })
                )

                /*
                Shard_ServerboundPlayerActionPacket.attach(
                    "fsdfsdfsd",
                    Main.instance,
                    event.player,
                ) {

                    println(this.sequence)
                    println(this.action)
                    println(this.direction)
                    println(this.z)
                    println(this.x)
                    println(this.y)
                }
                 */

                val head = TextHeads.generateHead(
                    UUID.fromString("f6f3a530-6c39-4098-96a0-6bdf4f3afc70"), true
                )


                event.player.sendMessage(message)


                /*
                Shard_ServerboundInteractPacket.attach(
                    "cosdfsfgdfgfs",
                    Main.instance,
                    event.player,
                ) {

                }
                 */

                // event.player.sendMessage(message)


                // event.player.sendMessage(
                //   MINI_MESSAGE.deserialize("\uF001 \uF002 \uF003 \uF004 \uF005").font(Key.key("crystalshard:toasts"))
                // )

                event.player.sendMessage(
                    Component.text().text("Current Page:").text("Cool") {
                        copyToClipboard("Cool text")
                        textTooltip(MINI_MESSAGE.deserialize("<color:#ff8373>Welcome to the Server.</color>\n\n<gray>Please verify your Minecraft Account with <color:#1c8aff>Discord</color>.</gray>"))
                    }.build()
                )

                // event.player.openInventory(basicGui(3))
                event.player.toast("Cool", Toast.ToastTypes.ADVANCEMENT)

                SimulacrumFactory.createDisPlayEntity<PTextDisplay>(
                    NamespacedKey("sdfds", "dfgdf"),
                    EntityType.TEXT_DISPLAY,
                    Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0),
                    mutableListOf(event.player)
                ) {
                    display = entity

                    ClientPacketFactory.setEntityDataPacket(
                        this.entity.entityId, mutableListOf(
                            EntityMetadata<Byte>(
                                index = 27,
                                type = EntityDataSerializerType.BYTE,
                                value = (0x01 or 0x02).toByte()
                            )
                        )
                    ) { packet ->
                        packet.send(mutableListOf(event.player))
                    }

                    text(
                        message, mutableListOf(event.player)
                    )

                    onInteract(
                        NamespacedKey("crystalshardtest", "playerjoindisplaydetect"),
                        Main.instance,
                        Pair(2.0F, 2.0F),
                        event.player
                    ) {
                        event.player.sendMessage(
                            data.text!!
                        )
                    }

                    onHover(Main.instance, event.player, 0.90) { isLockingAt ->
                        if (isLockingAt) {
                            // println("Lock: ${event.player.name}")


                            ClientPacketFactory.setEntityDataPacket(
                                entity.entityId, mutableListOf(
                                    EntityMetadata(
                                        index = 12, type = EntityDataSerializerType.VECTOR3, value = Vector3f(2.0F, 2.0F, 2.0F)
                                    )
                                )
                            ) { packet ->
                                packet.send(mutableListOf(event.player))
                            }


                        } else {
                            // println("NotLock: ${event.player.name}")


                            ClientPacketFactory.setEntityDataPacket(
                                entity.entityId, mutableListOf(
                                    EntityMetadata(
                                        index = 12, type = EntityDataSerializerType.VECTOR3, value = Vector3f(1.0F, 1.0F, 1.0F)
                                    )
                                )
                            ) { packet ->
                                packet.send(mutableListOf(event.player))
                            }


                        }

                    }
                }

                SimulacrumFactory.createNpc<Npc>(
                    Location(Main.instance.server.worlds.first(), 0.0, 0.0, 0.0), NamespacedKey("test", "test"), "test"
                ) {

                    player = playerEntity
                    playerEntity.playerListName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))
                    event.player.playerListName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))
                    playerEntity.displayName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))
                    event.player.displayName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))
                    playerEntity.customName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))
                    event.player.customName(MINI_MESSAGE.deserialize("<gray>NPC</gray>"))

                    playerEntity.isCustomNameVisible = true
                    event.player.isCustomNameVisible = true

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

                    ClientPacketFactory.playerInfoUpdatePacket(
                        playerEntity,
                        actions,
                    ) { packet ->
                        packet.send(mutableListOf(event.player))
                    }

                    ClientPacketFactory.addEntitiesPacket(
                        entityId = playerEntity.entityId,
                        entityUUID = playerEntity.uniqueId,
                        location = playerEntity.location,
                        entityType = EntityType.PLAYER,
                        data = 0,
                        yHeadRot = 0.0,
                    ) { packet ->
                        packet.send(mutableListOf(event.player))
                    }

                    val equipmentList: MutableList<Pair<EquipmentSlot, ItemStack>> = mutableListOf()
                    equipmentList.add(
                        Pair(
                            EquipmentSlot.MAINHAND, ItemStack(Material.STONE_SHOVEL)
                        )
                    )

                    ClientPacketFactory.createEquipmentPacket(
                        playerEntity.entityId, equipmentList
                    ) { packet ->
                        packet.send(mutableListOf(event.player))
                    }


                    ClientPacketFactory.setEntityDataPacket(
                        playerEntity.entityId, mutableListOf(
                            EntityMetadata<Byte>(
                                index = 16,
                                type = EntityDataSerializerType.DATA_PLAYER_MODE_CUSTOMISATION,
                                value = (0x01 or 0x02 or 0x04 or 0x08 or 0x10 or 0x20 or 0x40).toByte()
                            )
                        )
                    ) { packet ->
                        packet.send(mutableListOf(event.player))
                    }


                    ClientPacketFactory.teleportEntityPacket(
                        player!!.entityId,
                        Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0),
                        false
                    ) { packet ->
                        packet.send(mutableListOf(event.player))
                    }


                    ServerPacketFactory.interactEvent(
                        Shard_ServerPacketData(
                            player = event.player, name = NamespacedKey(Main.instance, "testnpcinteraction"), plugin =Main.instance
                        )
                    ) {
                        event.player.sendMessage("COOL")

                        val fakeDisplay = SimulacrumFactory.createEntityInstance<ItemDisplay>(
                            EntityType.ITEM_DISPLAY,
                            Location(Bukkit.getWorld("world"), 1.0, 1.0, 1.0)
                        ) {

                            ClientPacketFactory.addEntitiesPacket(
                                this.entityId,
                                this.uniqueId,
                                location,
                                EntityType.ITEM_DISPLAY,
                                0,
                                0.0
                            ) { packet ->
                                packet.send(mutableListOf(event.player))
                            }
                        }

                        ClientPacketFactory.setPassengersPacket(fakeDisplay, mutableListOf(playerEntity)) { packet ->
                            packet.send(mutableListOf(event.player))
                        }
                    }


                }
         */
    }
}