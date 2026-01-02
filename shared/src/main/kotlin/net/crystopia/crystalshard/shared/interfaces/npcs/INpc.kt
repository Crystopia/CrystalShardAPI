package net.crystopia.crystalshard.shared.interfaces.npcs

import com.mojang.authlib.GameProfile
import net.crystopia.crystalshard.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.shared.config.npc.NpcSkinData
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EquipmentSlot
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * Default NPC Interface with use full variables to interact with an NPC
 */
interface INpc {

    var gameProfile: GameProfile
    var playerEntity: ServerPlayer
    var isTeamCreated: MutableMap<UUID?, Boolean?>?
    var isVisibleForPlayer: MutableMap<UUID?, Boolean?>?
    var isLookingAtPlayer: MutableMap<UUID?, Boolean?>?
    var id: NamespacedKey
    var entityId: Int
    val name: String?
    val permission: String?
    val skin: NpcSkinData?
    val mirrorSkin: Boolean?
    val location: Location?
    val actions: EnumSet<InfoUpdateAction>?
    val glowing: Boolean?
    val type: EntityType?
    val equipment: MutableMap<EquipmentSlot?, ItemStack?>?
    val turnToPlayer: Boolean?
    val turnToPlayerDistance: Int?
    val spawnOnJoin: Boolean?
    fun spawn(player: Player)
    fun spawnAll()
    fun remove(player: Player)
    fun removeAll()
}