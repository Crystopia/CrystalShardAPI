package net.crystopia.crystalshard.extra.npc

import com.mojang.authlib.GameProfile
import com.mojang.datafixers.util.Pair
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.CrystalShard
import net.minecraft.Optionull
import net.minecraft.network.chat.RemoteChatSession
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.*
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.PositionMoveRotation
import net.minecraft.world.entity.Relative
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.Vec3
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import java.util.*
import java.util.function.Function


object NPCUtil {

    fun injectToPLayer(player: Player, npc: INpc): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()["PacketInjector"] != null) {
            return false
        }

        NpcManager.register(npc.id, npc)

        channel.pipeline().addAfter(
            "decoder", "PacketInjector", object : MessageToMessageDecoder<ServerboundInteractPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundInteractPacket, out: MutableList<Any>
                ) {
                    CrystalShard.plugin.server.scheduler.runTaskLater(
                        CrystalShard.plugin,
                        Runnable {
                            val interactPacket = msg.entityId
                            NpcManager.getNpcByEntityId(interactPacket)!!.onInteract(player)
                        },
                        1L
                    );
                }
            })

        return true
    }

    object Packets {

        fun makePlayerInfoUpdatePacketEntry(
            npcPlayer: ServerPlayer, gameProfile: GameProfile
        ): ClientboundPlayerInfoUpdatePacket.Entry {

            return ClientboundPlayerInfoUpdatePacket.Entry(
                npcPlayer.getUUID(),
                gameProfile,
                true,
                0,
                npcPlayer.gameMode.gameModeForPlayer,
                npcPlayer.tabListDisplayName,
                true,
                -1,
                Optionull.map<RemoteChatSession?, RemoteChatSession.Data?>(
                    npcPlayer.chatSession, Function { obj: RemoteChatSession? -> obj!!.asData() })
            )
        }
        
        fun <T : Player> setEquipmentPacket(
            player: T,
            entityId: Int,
            equipmentList: MutableList<Pair<EquipmentSlot?, ItemStack?>?>
        ) {
            val setEquipmentPacket = ClientboundSetEquipmentPacket(
                entityId, equipmentList
            )
            sendPacket(setEquipmentPacket, player)
        }

        fun <T : Player> setEntityDataPacket(
            player: T,
            entityId: Int,
            entityData: MutableList<SynchedEntityData.DataValue<*>>
        ) {
            val data: MutableList<SynchedEntityData.DataValue<*>> = mutableListOf()
            for (dataItem in entityData) {
                data.add(dataItem)
            }

            val setEquipmentPacket = ClientboundSetEntityDataPacket(
                entityId, data
            )
            sendPacket(setEquipmentPacket, player)
        }

        fun <T : Player> teleportEntityPacket(
            player: T,
            entityId: Int,
            location: org.bukkit.Location
        ) {
            val teleportEntityPacket = ClientboundTeleportEntityPacket(
                entityId,
                PositionMoveRotation(
                    Vec3(location.x, location.y, location.z),
                    Vec3.ZERO,
                    location.yaw,
                    location.pitch
                ),
                mutableSetOf<Relative?>(),
                false
            )
            sendPacket(teleportEntityPacket, player)
        }

        fun <T : Player> playerInfoRemovePacket(
            player: T,
            uuids: MutableList<UUID>
        ) {
            val playerInfoRemovePacket = ClientboundPlayerInfoRemovePacket(uuids)
            sendPacket(playerInfoRemovePacket, player)
        }

        fun <T : Player> playerInfoUpdatePacket(
            player: T,
            npcPlayer: ServerPlayer,
            gameProfile: GameProfile,
            actions: EnumSet<ClientboundPlayerInfoUpdatePacket.Action>
        ) {
            val playerInfoPacket = ClientboundPlayerInfoUpdatePacket(
                actions,
                makePlayerInfoUpdatePacketEntry(npcPlayer, gameProfile)
            )
            sendPacket(playerInfoPacket, player)
        }

        fun <T : Player> removeEntitiesPacket(
            player: T,
            entityId: Int
        ) {
            val playerInfoPacket = ClientboundRemoveEntitiesPacket(entityId)
            sendPacket(playerInfoPacket, player)
        }

        fun <T : Player> rotateHeadPacket(
            player: T,
            entity: net.minecraft.world.entity.Entity,
            yaw: Float
        ) {
            val angelMultiplier = 256F / 360F
            val playerInfoPacket = ClientboundRotateHeadPacket(
                entity,
                ((yaw * angelMultiplier).toInt().toByte())
            )
            sendPacket(playerInfoPacket, player)
        }

        fun <T : Player> addEntityPacket(
            player: T,
            entityId: Int,
            entityUUID: UUID,
            entityType: net.minecraft.world.entity.EntityType<*>,

            x: Double?,
            y: Double?,
            z: Double?,
            xRot: Float?,
            yRot: Float?,
            data: Int?,
            deltaMovement: Vec3?,
            yHeadRot: Double?,
        ) {
            val addEntityPacket = ClientboundAddEntityPacket(
                entityId,
                entityUUID,
                x ?: 0.0,
                y ?: 0.0,
                z ?: 0.0,
                xRot ?: 0.0F,
                yRot ?: 0.0F,
                entityType,
                data ?: 0,
                deltaMovement ?: Vec3.ZERO,
                yHeadRot ?: 0.0
            )
            sendPacket(addEntityPacket, player)
        }

        fun sendPacket(packet: Packet<*>, player: Player) {
            val serverPlayer = (player as CraftPlayer).handle
            serverPlayer.connection.send(packet)
        }
    }

}