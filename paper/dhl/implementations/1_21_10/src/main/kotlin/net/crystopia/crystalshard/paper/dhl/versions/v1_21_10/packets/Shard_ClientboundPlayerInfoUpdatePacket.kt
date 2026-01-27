package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import com.mojang.authlib.GameProfile
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundPlayerInfoUpdatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.Optionull
import net.minecraft.network.chat.RemoteChatSession
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.server.level.ServerPlayer
import org.bukkit.craftbukkit.entity.CraftPlayer
import java.util.*
import java.util.function.Function


class Shard_ClientboundPlayerInfoUpdatePacket : IPacket<ClientboundPlayerInfoUpdatePacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerInfoUpdatePacketData
    ): ClientboundPlayerInfoUpdatePacket {
        val serverPlayer = (packetObj.serverPlayer as CraftPlayer).handle
        val vanillaActions =
            EnumSet.noneOf<ClientboundPlayerInfoUpdatePacket.Action?>(ClientboundPlayerInfoUpdatePacket.Action::class.java)

        packetObj.actions.forEach { infoUpdateAction ->
            when (infoUpdateAction) {
                net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction.ADD_PLAYER -> {
                    vanillaActions.add(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER)
                }

                net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction.UPDATE_LATENCY -> {
                    vanillaActions.add(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LATENCY)
                }

                net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction.UPDATE_LISTED -> {
                    vanillaActions.add(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED)
                }

                net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction.UPDATE_GAME_MODE -> {
                    vanillaActions.add(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_GAME_MODE)
                }

                net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction.UPDATE_DISPLAY_NAME -> {
                    vanillaActions.add(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME)
                }

                net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction.UPDATE_HAT -> {
                    vanillaActions.add(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_HAT)
                }

                net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction.INITIALIZE_CHAT -> {
                    vanillaActions.add(ClientboundPlayerInfoUpdatePacket.Action.INITIALIZE_CHAT)
                }

                net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction.UPDATE_LIST_ORDER -> {
                    vanillaActions.add(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LIST_ORDER)
                }

            }
        }

        return ClientboundPlayerInfoUpdatePacket(
            vanillaActions, makeEntry(serverPlayer, serverPlayer.gameProfile)
        )
    }

    private companion object {
        private fun makeEntry(
            serverPlayer: ServerPlayer, gameProfile: GameProfile
        ): ClientboundPlayerInfoUpdatePacket.Entry {
            return ClientboundPlayerInfoUpdatePacket.Entry(
                serverPlayer.getUUID(),
                gameProfile,
                true,
                0,
                serverPlayer.gameMode.gameModeForPlayer,
                serverPlayer.tabListDisplayName,
                true,
                serverPlayer.tabListOrder,
                Optionull.map<RemoteChatSession?, RemoteChatSession.Data?>(
                    serverPlayer.chatSession, Function { obj: RemoteChatSession? -> obj!!.asData() })
            )
        }
    }

}