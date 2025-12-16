package net.crystopia.crystalshard.extra.npc

import com.mojang.authlib.GameProfile
import net.minecraft.Optionull
import net.minecraft.network.chat.RemoteChatSession
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.server.level.ServerPlayer
import java.util.function.Function

object NpcPackets {

    fun getPlayerInfoUpdatePacketEntry(
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
}