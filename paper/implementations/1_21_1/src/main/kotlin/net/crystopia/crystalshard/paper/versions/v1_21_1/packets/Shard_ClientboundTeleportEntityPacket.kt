package net.crystopia.crystalshard.paper.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundTeleportEntityPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.shared.util.ClassUtil
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.monster.Zombie
import net.minecraft.world.phys.Vec3
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftServer
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.entity.TextDisplay

class Shard_ClientboundTeleportEntityPacket : IPacket<ClientboundTeleportEntityPacketData> {

    override fun createPacket(
        packetObj: ClientboundTeleportEntityPacketData
    ): ClientboundTeleportEntityPacket {

        val entity = Display.TextDisplay(
            EntityType.TEXT_DISPLAY,
            (Bukkit.getWorlds()[0] as CraftWorld).handle,
        )

        entity.id = packetObj.entityId
        entity.setPos(packetObj.location.x, packetObj.location.y, packetObj.location.z)
        entity.yRot = packetObj.location.yaw
        entity.xRot = packetObj.location.pitch
        entity.onGround = packetObj.onGround

        return ClientboundTeleportEntityPacket(entity)
    }
}