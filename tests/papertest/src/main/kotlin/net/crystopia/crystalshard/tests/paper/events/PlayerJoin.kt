package net.crystopia.crystalshard.tests.paper.events

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMultimap
import com.google.common.io.ByteStreams
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import com.mojang.authlib.properties.PropertyMap
import gg.flyte.twilight.gui.GUI.Companion.openInventory
import gg.flyte.twilight.gui.gui
import net.crystopia.crystalshard.paper.core.CrystalShard
import net.crystopia.crystalshard.paper.core.displays.PTextDisplay
import net.crystopia.crystalshard.paper.core.extension.MINI_MESSAGE
import net.crystopia.crystalshard.paper.core.extension.copyToClipboard
import net.crystopia.crystalshard.paper.core.extension.text
import net.crystopia.crystalshard.paper.core.extension.textTooltip
import net.crystopia.crystalshard.paper.core.factories.EntityFactory
import net.crystopia.crystalshard.paper.core.factories.PacketFactory
import net.crystopia.crystalshard.paper.core.messaging.ChannelType
import net.crystopia.crystalshard.paper.core.messaging.MessageType
import net.crystopia.crystalshard.paper.core.messaging.PluginMessage
import net.crystopia.crystalshard.paper.core.npc.Npc
import net.crystopia.crystalshard.paper.core.packets.ServerboundInteractPacketUtil
import net.crystopia.crystalshard.paper.core.resourcepacks.TextHeads
import net.crystopia.crystalshard.paper.core.resourcepacks.toGuiRow
import net.crystopia.crystalshard.paper.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.tests.paper.Main
import net.kyori.adventure.key.Key
import net.kyori.adventure.nbt.api.BinaryTagHolder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.DataComponentValue
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.phys.Vec3
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.joml.Vector3f
import java.util.*

object PlayerJoin : Listener {

    lateinit var display: Display.TextDisplay

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

    @EventHandler
    fun addNPCsOnJoin(event: PlayerJumpEvent) {

        PluginMessage(
            channelType = ChannelType.IN,
            plugin = Main.instance,
            channel = "BungeeCord",
            messageType = MessageType.PLAYER_COUNT.channel,
            onMessage = fun(
                channel: String,
                player: Player,
                message: ByteArray,
            ) {
                val data = ByteStreams.newDataInput(message);
                val subchannel = data.readUTF();
                if (subchannel.equals("PlayerCount")) {
                    // This is our response to the PlayerCount request
                    val server = data.readUTF();
                    val playerCount = data.readInt();
                    player.sendMessage("Player count of server $server is $playerCount")
                }

            }
        ).register().send("lobby")
    }

    @EventHandler
    fun addNPCsOnJoin(event: PlayerJoinEvent) {

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
        // event.player.toast("Cool")

        EntityFactory.createDisPlayEntity<PTextDisplay>(
            NamespacedKey("sdfds", "dfgdf"),
            EntityType<*>.TEXT_DISPLAY,
            Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0),
            mutableListOf(event.player)
        ) {
            entity as Display.TextDisplay

            display = entity as Display.TextDisplay

            text(
                message, mutableListOf(event.player)
            )

            val accessor = EntityDataAccessor(12, EntityDataSerializers.VECTOR3)
            onHover(Main.instance, event.player, 0.90) { isLockingAt ->


                if (isLockingAt) {
                    // println("Lock: ${event.player.name}")

                    PacketFactory.setEntityDataPacket(
                        entity.id, mutableListOf(
                            SynchedEntityData.DataValue.create(
                                accessor, Vector3f(2.0F, 2.0F, 2.0F)
                            )
                        )
                    ) { packet ->
                        PacketFactory.sendPacket(packet, mutableListOf(event.player))
                    }
                } else {
                    // println("NotLock: ${event.player.name}")

                    PacketFactory.setEntityDataPacket(
                        entity.id, mutableListOf(
                            SynchedEntityData.DataValue.create(
                                accessor, Vector3f(1.0F, 1.0F, 1.0F)
                            )
                        )
                    ) { packet ->
                        PacketFactory.sendPacket(packet, mutableListOf(event.player))
                    }
                }

            }

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


        }


        EntityFactory.createNpc<Npc>(
                    Location(CrystalShard.plugin.server.worlds.first(), 0.0, 0.0, 0.0),
                    NamespacedKey("test", "test"),
                    "I'm a NPC"
                ) {

            val actions = EnumSet.noneOf(InfoUpdateAction::class.java)
            actions.add(InfoUpdateAction.ADD_PLAYER)
            actions.add(InfoUpdateAction.UPDATE_DISPLAY_NAME)
            actions.add(InfoUpdateAction.UPDATE_LISTED)


            val value: String =
                "ewogICJ0aW1lc3RhbXAiIDogMTc0MjA2NTk1NzMxOCwKICAicHJvZmlsZUlkIiA6ICIzYjBmNTM5MmRlNzM0YmZjYmJkOTMxYzMxYmFkODMxMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJjYXRhbmRCIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxNjNhNjNiMDliNTE4MzY4YmU0ZDhlYTA5ODg0NTM4ZGUxNTRlYjU0Y2ZlYjA4ZjA5NmE2Y2Y0NDdjZWZkMzUiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="
            val signature: String =
                "ElfmMCz1IPrR4+PdwXRk0AbV5CO3/jrSU4ciTq8DdrYgQW1KMM3OVYyz8dig6P4dWaCaQpOUMMEdyNuqaTKCqO4Jjznk95DTziOlCU0aPVjRCdWElE0oU3xP3nGYIWw/O/jUPD1z+0bkFE0l4gwEL+QBKYjV/tOTNZAWuNuOSRc/lnrpxFPmpVCypq5Mk/e34ZAcQFZPn+MjTrLyBTJfl7A6PrKEKg5zXBIRGOYZ8qCfJtLZOzJPmKF9gtTUmFM7WzmrJTD8+dD66JEbeoSIMOdw0AC8EMm9HL2Ahmd8/1NqtT9WlHvQGb8ItL/JtuygusnA+o5bVnzXLK4i+DXy5dojlJOMNKJJg8AULhrBQNH2ZUGIvJ9mR8re6HFOVqiRtJfXoVYhzJR0PFekb9JgCH0ZKBewPtYjHhviSscxd735c0BeVVli0AqB0POcMkGkEefYTFcVMxKSC2epKbwTqn5vvxF5j18cYBgyUzxIMF1QNH06CAH4Hj60LUbBfcJdtq2A43xmrXRNb8Hp/+9t4tXtyoUWnFfDSjS4vem6JRZ5qaMHzxfeD4e0ejqG7D7zscDEA5+SYTqOazLJPjfbucTVkeyH1Ec6kUHJmoOcf7lmryCHVilZQVLyWEpV0a7aPf9AOzB/tjJSKYzpovWlybF9X3MDNOUPKaGoSyYTj6I="

            val properties = PropertyMap(
                ImmutableMultimap.Builder<String, Property>().put(
                    "textures", Property(
                        "textures", value, signature
                    )
                ).build()
            )

            playerEntity.gameProfile = GameProfile(playerEntity.uuid, playerEntity.displayName, properties)


            CrystalShard.plugin.server.onlinePlayers.forEach { player ->
                PacketFactory.playerInfoUpdatePacket(
                    playerEntity,
                    playerEntity.gameProfile,
                    actions,
                ) { packet ->
                    PacketFactory.sendPacket(packet, mutableListOf(player))
                }
            }

            playerEntity.setPos(0.0, 0.0, 0.0)

            PacketFactory.addEntitiesPacket(
                entityId = playerEntity.id,
                entityUUID = playerEntity.uuid,
                location = Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0, 0.0F, 0.0F),
                entityType = playerEntity.type,
                data = 0,
                deltaMovement = Vec3.ZERO,
                yHeadRot = 0.0,
            ) { packet ->
                PacketFactory.sendPacket(packet, Bukkit.getServer().onlinePlayers.toMutableList())
            }

            val equipmentList: MutableList<com.mojang.datafixers.util.Pair<EquipmentSlot, net.minecraft.world.item.ItemStack>> =
                mutableListOf()
            equipmentList.add(
                com.mojang.datafixers.util.Pair(
                    EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(
                        ItemStack(Material.STONE_SHOVEL)
                    )
                )
            )


            PacketFactory.createEquipmentPacket(
                playerEntity.id, equipmentList
            ) { equipmentPacket ->
                PacketFactory.sendPacket(equipmentPacket, CrystalShard.plugin.server.onlinePlayers.toMutableList())
            }

            PacketFactory.setEntityDataPacket(
                playerEntity.id, mutableListOf(
                    SynchedEntityData.DataValue.create(
                        net.minecraft.world.entity.player.Player.DATA_PLAYER_MODE_CUSTOMISATION,
                        (0x01 or 0x02 or 0x04 or 0x08 or 0x10 or 0x20 or 0x40).toByte()
                    )
                )
            ) { packet ->
                PacketFactory.sendPacket(packet, CrystalShard.plugin.server.onlinePlayers.toMutableList())
            }

            ServerboundInteractPacketUtil.attach("TestNPCInteraction", Main.instance, event.player) { clickType, msg ->

                val entity = Display.TextDisplay(EntityType.TEXT_DISPLAY, playerEntity.level())

                entity.setPos(location!!.x, location!!.y, location!!.z)
                PacketFactory.addEntitiesPacket(
                    entity.id, entity.uuid,
                    location = location!!,
                    entityType = EntityType.TEXT_DISPLAY,
                    data = 0,
                    deltaMovement = Vec3.ZERO,
                    yHeadRot = 0.0,
                ) { packet ->
                    PacketFactory.sendPacket(packet, Bukkit.getServer().onlinePlayers.toMutableList())
                }

                entity.passengers = ImmutableList.of(playerEntity);

                PacketFactory.setPassengersPacket(entity) { packet ->
                    PacketFactory.sendPacket(packet, Bukkit.getServer().onlinePlayers.toMutableList())
                }
            }
        }
    }
    }



