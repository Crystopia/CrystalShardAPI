package net.crystopia.crystalshard.tests.paper.events

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import com.destroystokyo.paper.profile.ProfileProperty
import gg.flyte.twilight.gui.GUI.Companion.openInventory
import gg.flyte.twilight.gui.gui
import net.crystopia.crystalshard.common.extension.MINI_MESSAGE
import net.crystopia.crystalshard.common.extension.copyToClipboard
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.common.extension.textTooltip
import net.crystopia.crystalshard.paper.dhl.PacketFactory
import net.crystopia.crystalshard.paper.dhl.server.ServerboundContainerClickPacketUtil
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.*
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityDataSerializerType
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.paper.pack.font.TextHeads
import net.crystopia.crystalshard.paper.pack.font.toGuiRow
import net.crystopia.crystalshard.paper.pack.toasts.Toast
import net.crystopia.crystalshard.paper.pack.toasts.toast
import net.crystopia.crystalshard.paper.simulacrum.SimulacrumFactory
import net.crystopia.crystalshard.paper.simulacrum.displays.PTextDisplay
import net.crystopia.crystalshard.paper.simulacrum.npc.Npc
import net.crystopia.crystalshard.tests.paper.Main
import net.kyori.adventure.key.Key
import net.kyori.adventure.nbt.api.BinaryTagHolder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.DataComponentValue
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.entity.TextDisplay
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryType
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
    @EventHandler
    fun onJump(event: PlayerJumpEvent) {

        PacketFactory.teleportEntityPacket(
            player!!.entityId,
            Location(Bukkit.getWorld("world"), 1.0, 1.0, 1.0),
            false
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }

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

        // TODO: HERE
        ServerboundContainerClickPacketUtil.attach(
            "sdffds",
            Main.instance,
            event.player,
            mutableListOf(item, item2)
        ) {
            event.player.sendMessage(
                Component.text().text("${this.clickType} - $containerId - ${this.slotNum}").build()
            )
        }

        /*
        ServerboundUseItemPacketUtil.attach("sdsdf", Main.instance, event.player) {
            this.hand
            event.player.sendMessage(this.sequence.toString())
        }

        ServerboundUseItemOnPacketUtil.attach("sdfsdfgfgdfgdfgdf", Main.instance, event.player) {
            this.hand
            event.player.sendMessage(this.hitResult.type.toString())
        }

        ServerboundSelectTradePacketUtil.attach("sdgdfgdgdgdsfgdfgfdgdfgdfgdfg", Main.instance, event.player) {
            event.player.sendMessage(this.item.toString())
        }
         */

        PacketFactory.openScreenPacket(
            3243443,
            Component.text().text("<rainbow>Nice Small GUI</rainbow>").build(),
            net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.MenuType.GENERIC_9x6
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }



        PacketFactory.setContainerSlot(
            3243443,
            0,
            0,
            item
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }

        PacketFactory.setContainerSlot(
            3243443,
            0,
            1,
            item2
        ) { packet ->
            packet.send(mutableListOf(event.player))
        }

        PacketFactory.sendWaypoint(
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

    }

    @EventHandler
    fun addNPCsOnJoin(event: PlayerJoinEvent) {
        if (event.player.name != "xyzjesper") return
        Main.instance.adv.complete(event.player) {

        }
        val head = TextHeads.generateHead(
            UUID.fromString("f6f3a530-6c39-4098-96a0-6bdf4f3afc70"), true
        )
        val message = Component.text("Dein Code: ", NamedTextColor.GRAY).append(
            Component.text("ABC-123", NamedTextColor.GREEN).clickEvent(ClickEvent.copyToClipboard("ABC-123"))
                .hoverEvent(HoverEvent.showText(Component.text("Klicken zum Kopieren")))
        )

        // event.player.sendMessage(message)


        // event.player.sendMessage(
        //   MINI_MESSAGE.deserialize("\uF001 \uF002 \uF003 \uF004 \uF005").font(Key.key("crystalshard:toasts"))
        // )

        val values: MutableMap<Key, DataComponentValue> = mutableMapOf()

        values[Key.key("minecraft:custom_name")] = BinaryTagHolder.binaryTagHolder("Cool")

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
            net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityType.TEXT_DISPLAY,
            Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0),
            mutableListOf(event.player)
        ) {
            display = entity as TextDisplay

            text(
                message, mutableListOf(event.player)
            )

            /*
            onHover(Main.instance, event.player, 0.90) { isLockingAt ->


                if (isLockingAt) {
                    // println("Lock: ${event.player.name}")


                    PacketFactory.setEntityDataPacket(
                        entity.entityId, mutableListOf(
                            EntityMetadata(
                                index = 12,
                                type = EntityDataSerializerType.VECTOR3,
                                value = Vector3f(2.0F, 2.0F, 2.0F)
                            )
                        )
                    ) { packet ->
                        packet.send(mutableListOf(event.player))
                    }


                } else {
                    // println("NotLock: ${event.player.name}")


                    PacketFactory.setEntityDataPacket(
                        entity.entityId, mutableListOf(
                            EntityMetadata(
                                index = 12,
                                type = EntityDataSerializerType.VECTOR3,
                                value = Vector3f(1.0F, 1.0F, 1.0F)
                            )
                        )
                    ) { packet ->
                        packet.send(mutableListOf(event.player))
                    }


                }

            }
             */

            /*
            onInteract(
                NamespacedKey("crystalshardtest", "playerjoindisplaydetect"),
                Main.instance,
                Pair(2.0F, 2.0F),
                event.player
            ) { clickType, msg ->
                event.player.sendMessage(
                    data.text!!
                )
            }
             */
        }

        SimulacrumFactory.createNpc<Npc>(
            Location(Main.instance.server.worlds.first(), 0.0, 0.0, 0.0),
                    NamespacedKey("test", "test"),
                    "I'm a NPC"
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

            val actions = EnumSet.noneOf(InfoUpdateAction::class.java)
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


            PacketFactory.playerInfoUpdatePacket(
                    playerEntity,
                    actions,
                ) { packet ->
                packet.send(mutableListOf(event.player))
                }


            playerEntity.location.x = 5.0
            playerEntity.location.y = 0.0
            playerEntity.location.z = 0.0

            PacketFactory.addEntitiesPacket(
                entityId = playerEntity.entityId,
                entityUUID = playerEntity.uniqueId,
                location = playerEntity.location,
                entityType = net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityType.PLAYER,
                data = 0,
                yHeadRot = 0.0,
            ) { packet ->
                packet.send(mutableListOf(event.player))
            }

            val equipmentList: MutableList<Pair<net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EquipmentSlot, ItemStack>> =
                mutableListOf()
            equipmentList.add(
                Pair(
                    net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EquipmentSlot.MAINHAND,
                    ItemStack(Material.STONE_SHOVEL)
                )
            )

            PacketFactory.createEquipmentPacket(
                playerEntity.entityId, equipmentList
            ) { packet ->
                packet.send(mutableListOf(event.player))
            }

            PacketFactory.setEntityDataPacket(
                playerEntity.entityId, mutableListOf(
                    EntityMetadata<Byte>(
                        index = 16,
                        type = EntityDataSerializerType.BYTE,
                        value = (0x01 or 0x02 or 0x04 or 0x08 or 0x10 or 0x20 or 0x40).toByte()
                    )
                )
            ) { packet ->
                packet.send(mutableListOf(event.player))
            }

            /*
             ServerboundInteractPacketUtil.attach("TestNPCInteraction", Main.instance, event.player) { clickType, msg ->

                 event.player.sendMessage("COOL")

                 val fakeDisplay = SimulacrumFactory.createEntityInstance<ItemDisplay>(
                     EntityType.ITEM_DISPLAY,
                     Location(Bukkit.getWorld("world"), 1.0, 1.0, 1.0)
                 ) {

                     PacketFactory.addEntitiesPacket(
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

                 PacketFactory.setPassengersPacket(fakeDisplay, mutableListOf(playerEntity)) { packet ->
                     packet.send(mutableListOf(event.player))
                 }
             }
             */
        }
    }

}



