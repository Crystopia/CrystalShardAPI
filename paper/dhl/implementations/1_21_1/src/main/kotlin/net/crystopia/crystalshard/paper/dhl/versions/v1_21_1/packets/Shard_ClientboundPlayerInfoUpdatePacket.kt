package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import com.mojang.authlib.GameProfile
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundPlayerInfoUpdatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.server.level.ServerPlayer
import java.util.*

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
            vanillaActions, makeEntry(
                serverPlayer, packetObj.gameProfile
            )
        )
    }

    private companion object {
        private fun makeEntry(
            serverPlayer: ServerPlayer, gameProfile: GameProfile
        ): ClientboundPlayerInfoUpdatePacket.Entry {

            return ClientboundPlayerInfoUpdatePacket.Entry(
                serverPlayer.uuid,
                gameProfile,
                true,
                0,
                serverPlayer.gameMode.gameModeForPlayer,
                serverPlayer.tabListDisplayName,
                null
            )
        }
    }
}