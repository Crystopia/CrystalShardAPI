package net.crystopia.crystalshard.extra.npc

import com.mojang.authlib.properties.Property
import net.crystopia.crystalshard.extra.npc.config.NpcVisibility
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.world.entity.EquipmentSlot
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*
import java.util.function.Consumer


interface INpc {

    var isTeamCreated: MutableMap<UUID?, Boolean?>
    var isVisibleForPlayer: MutableMap<UUID?, Boolean?>
    var isLookingAtPlayer: MutableMap<UUID?, Boolean?>
    var lastPlayerInteraction: MutableMap<UUID?, Long?>

    var id: NamespacedKey
    val name: String?
    val permission: String
    val skin: Property?
    val mirrorSkin: Boolean
    val location: Location?
    val actions: EnumSet<ClientboundPlayerInfoUpdatePacket.Action>
    val glowing: Boolean
    val type: EntityType?
    val equipment: MutableMap<EquipmentSlot?, ItemStack?>?
    val onInteract: Consumer<Player?>?
    val turnToPlayer: Boolean
    val turnToPlayerDistance: Int
    val visibilityDistance: Int
    val visibility: NpcVisibility?

    fun isShownFor(player: Player): Boolean {
        return isVisibleForPlayer.getOrDefault(player.uniqueId, false)!!
    }

    fun spawn(player: Player)
    fun spawnAll()
    fun remove(player: Player)
    fun removeAll()

}