package net.crystopia.crystalshard.paper.dhl.server

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.crystopia.crystalshard.paper.core.utils.Log
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket
import net.minecraft.world.phys.HitResult
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util class for attaching and working with the ServerboundUseItemOnPacket for user-defined interaction with items on [...] by the player.
 */
object ServerboundUseItemOnPacketUtil {

    data class UseItemOnEvent(
        var blockHit: BlockHitResult,
        var hand: ServerboundSwingPacketUtil.InteractionHand,
        var sequence: Int,
        var timestamp: Long
    )

    data class BlockHitResult(
        var direction: ServerboundPlayerActionPacketUtil.Direction,
        var x: Int,
        var y: Int,
        var z: Int,
        var type: ItemUseType,
        var inside: Boolean,
        var worldBorderHit: Boolean
    )

    enum class ItemUseType(val id: HitResult.Type) {
        MISS(HitResult.Type.MISS),
        BLOCK(HitResult.Type.BLOCK),
        ENTITY(HitResult.Type.ENTITY);

        companion object {
            var entries = ItemUseType.entries

            fun itemUseType(id: HitResult.Type): ItemUseType? {
                entries.forEach { itemUseType ->
                    if (itemUseType.id == id) {
                        return itemUseType
                    }
                }
                return null
            }
        }
    }

    /**
     * Attach the Event to the Player.
     */
    fun attach(
        name: String,
        plugin: JavaPlugin,
        player: Player,
        callback: UseItemOnEvent.() -> Unit
    ): Boolean {
        val serverPlayer = (player as CraftPlayer).handle
        val channel = serverPlayer.connection.connection.channel

        if (channel.pipeline()[name] != null) {
            return false
        }

        channel.pipeline().addAfter(
            "decoder", name, object : MessageToMessageDecoder<ServerboundUseItemOnPacket>() {
                override fun decode(
                    ctx: ChannelHandlerContext, msg: ServerboundUseItemOnPacket, out: MutableList<Any>
                ) {
                    out.add(msg)

                    plugin.server.scheduler.runTaskLater(
                        plugin,
                        Runnable {
                            callback(
                                UseItemOnEvent(
                                    blockHit = BlockHitResult(
                                        direction = ServerboundPlayerActionPacketUtil.Direction.direction(msg.hitResult.direction)!!,
                                        x = msg.hitResult.blockPos.x,
                                        y = msg.hitResult.blockPos.y,
                                        z = msg.hitResult.blockPos.z,
                                        type = ItemUseType.itemUseType(msg.hitResult.type)!!,
                                        inside = msg.hitResult.isInside,
                                        worldBorderHit = msg.hitResult.isWorldBorderHit
                                    ),
                                    hand = ServerboundSwingPacketUtil.InteractionHand.interactionHand(msg.hand)!!,
                                    sequence = msg.sequence,
                                    timestamp = msg.timestamp
                                )
                            )
                        },
                        1L
                    )
                }
            })

        return true
    }
}