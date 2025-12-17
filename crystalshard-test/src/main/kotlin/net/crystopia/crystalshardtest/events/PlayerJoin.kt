package net.crystopia.crystalshardtest.events

import com.google.common.collect.ImmutableMultimap
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import com.mojang.authlib.properties.PropertyMap
import com.mojang.datafixers.util.Pair
import net.crystopia.crystalshard.CrystalShard
import net.crystopia.crystalshard.builder.EntityBuilder
import net.crystopia.crystalshard.extra.npc.NPCUtil
import net.crystopia.crystalshard.utils.Log
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.Vec3
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import java.util.*


object PlayerJoin : Listener {

    @EventHandler
    fun addNPCsOnJoin(event: PlayerJoinEvent) {
        EntityBuilder.createNpc(
            CrystalShard.plugin.server.worlds.first(),
            NamespacedKey("test", "test"),
            "I'm a NPC"
        ) {
            playerEntity.setRot(0.0F, 0.0F)
            playerEntity.setYHeadRot(0.0F)
            playerEntity.xRot = 0.0F
            playerEntity.yRot = 0.0F
            playerEntity.setPos(0.0, 0.0, 0.0)

            val actions = EnumSet.noneOf(ClientboundPlayerInfoUpdatePacket.Action::class.java)
            actions.add(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER)
            actions.add(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME)
            actions.add(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED)
            
            
            val value: String = "ewogICJ0aW1lc3RhbXAiIDogMTc1NDA3NDg0NDk0MiwKICAicHJvZmlsZUlkIiA6ICI5OGQxYTQyNmRlMmU0NjBkYjdjNWExMmY5MGNhODg0OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJLdWJpbm9TSyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jMDc5NDZkNjJkMzNkYTg3YmVjOWRiNDNmNDRjMjRmMzM2MTFmZTMxZTE2OTllMDFkZTA1ZTI5YWZlYjhmMzcyIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0="
            val signature: String = "xblXSSWXrnkIy2R7OHWuwT+1Ky03cIkhfesnIrArAExN7iILwrDVHnVElF+l6N1wl+PjCx24yROXp+J5f2zTFC/LduuXB88lLv4Xw6TFMOU5FFp0kIgAXeYijOMQ8FhL4K50VUM30dIM1QUnfqEOHRf3s7DhUNr58PCxi01xUbEXJFNlGx5Nwjp8Q1yt87lwDwihasunKiCTEukgkUSptEa4xeZDRL/tZdZJUEML09757ErhSjzA1MdCX+YZOMrbov4PnA45z4x5Ms1lRu6BJ+tQqz0UMK2q4jKDdUYk6gm8nsXsBJUx4rTx2r4MgdBx0gqJ8EeV98ST9N2uyKV3yIfnTRrxGhi1DVKzT+atZZADf4GwMe1eU/5gkgjxL3I38Y9IfWG7X4w06p1bgV2muMj9zHl0rnXxfMLylgb6hUVqnJImvbrMU6fsBNGqbdD1v9hXhkMynldbfBnsVKDzf4gm5DHPs6IkYxWymw2tdjI2IY/nQ+C7ejiukMq46ABmazr0OK/CDnB5VnmCUNZo49cC4QJ9uxKQlrnpIm+ss0nKoOxmVESgo/vqj234jvUgJvSdOs9WSo6CPdW46dADt/rQUmWDASWAiN7sRm6iFT8X5QKFlPAiDMLvf+B1OdqUeGwXK1tUbjvYYs9/9b5JDOQrIByQ0z9fd7TdQKrB0sw="

            val propertyMap = PropertyMap(
                ImmutableMultimap.of<String?, Property?>(
                    "textures",
                    Property("textures", value, signature)
                )
            )


            val textures = this.playerEntity.gameProfile.properties().get("textures")
            if (textures.isEmpty()) {
                playerEntity.gameProfile = GameProfile(playerEntity.uuid, "", propertyMap)
            } else {
                val prop = textures.iterator().next()
                if (!prop.value().equals(value)) {
                    playerEntity.gameProfile = GameProfile(playerEntity.uuid, "", propertyMap)
                }
            }
            
            CrystalShard.plugin.server.onlinePlayers.forEach { player ->
                NPCUtil.Packets.playerInfoUpdatePacket(
                    player,
                    playerEntity,
                    playerEntity.gameProfile,
                    actions = actions
                )
            }


            playerEntity.setPos(0.0, 0.0, 0.0)

            val addEntityPacket = ClientboundAddEntityPacket(
                playerEntity.id, playerEntity.uuid, 0.0, 0.0, 0.0, 0.0F, 0.0F, EntityType.PLAYER, 0, Vec3.ZERO, 0.0
            )
            CrystalShard.plugin.server.onlinePlayers.forEach { player ->
                val serverPlayer = (player as CraftPlayer).handle
                serverPlayer.connection.send(addEntityPacket)
            }

            val equipmentList: MutableList<Pair<EquipmentSlot, ItemStack?>> = mutableListOf()
            equipmentList.add(
                Pair(
                    EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(
                        org.bukkit.inventory.ItemStack(Material.STONE_SHOVEL)
                    )
                )
            )

            val setEquipmentPacket = ClientboundSetEquipmentPacket(
                playerEntity.id, equipmentList
            )

            CrystalShard.plugin.server.onlinePlayers.forEach { player ->
                val serverPlayer = (player as CraftPlayer).handle
                serverPlayer.connection.send(setEquipmentPacket)
            }
            
            onInteract = { interactor ->

                Log.info("${interactor.name} Clicked on NPC with UUID ${this.playerEntity.uuid}")
                
            } 
            NPCUtil.injectToPLayer(event.player, this)
        }
    }

}