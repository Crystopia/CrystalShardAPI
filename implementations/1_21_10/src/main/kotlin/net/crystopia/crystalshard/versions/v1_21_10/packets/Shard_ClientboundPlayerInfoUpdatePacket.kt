package net.crystopia.crystalshard.versions.v1_21_10.packets


import com.mojang.authlib.GameProfile
import net.crystopia.crystalshard.shared.data.packets.ClientboundPlayerInfoUpdatePacketData
import net.crystopia.crystalshard.shared.interfaces.packets.IPacket
import net.minecraft.Optionull
import net.minecraft.network.chat.RemoteChatSession
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.server.level.ServerPlayer
import java.util.*
import java.util.function.Function


class Shard_ClientboundPlayerInfoUpdatePacket : IPacket<ClientboundPlayerInfoUpdatePacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerInfoUpdatePacketData
    ): ClientboundPlayerInfoUpdatePacket {

        val serverPlayer = packetObj.serverPlayer

        val vanillaActions =
            EnumSet.noneOf<ClientboundPlayerInfoUpdatePacket.Action?>(ClientboundPlayerInfoUpdatePacket.Action::class.java)
        for (action in packetObj.actions) {
            vanillaActions.add(ClientboundPlayerInfoUpdatePacket.Action.valueOf(action.toString()))
        }


        return ClientboundPlayerInfoUpdatePacket(
            vanillaActions, makeEntry(serverPlayer, packetObj.gameProfile)
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
                -1,
                Optionull.map<RemoteChatSession?, RemoteChatSession.Data?>(
                    serverPlayer.chatSession, Function { obj: RemoteChatSession? -> obj!!.asData() })
            )
        }
    }

}