package net.crystopia.crystalshard.versions.v1_21_1.packets


import net.crystopia.crystalshard.shared.data.packets.ClientboundTeleportEntityPacketData
import net.crystopia.crystalshard.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.shared.util.ClassUtil
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket

class Shard_ClientboundTeleportEntityPacket : IPacket<ClientboundTeleportEntityPacketData> {

    override fun createPacket(
        packetObj: ClientboundTeleportEntityPacketData
    ): ClientboundTeleportEntityPacket {

        return ClassUtil.initFakeInstance(
            ClientboundTeleportEntityPacket::class.java
        ) {
            setField("id", packetObj.entityId)
            setField("x", packetObj.location.x)
            setField("y", packetObj.location.y)
            setField("z", packetObj.location.z)
            setField("yRot", packetObj.location.yaw.toInt().toByte())
            setField("xRot", packetObj.location.pitch.toInt().toByte())
            setField("onGround", false)
        } as ClientboundTeleportEntityPacket
    }
}