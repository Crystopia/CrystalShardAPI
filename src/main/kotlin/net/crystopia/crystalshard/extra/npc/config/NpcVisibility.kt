package net.crystopia.crystalshard.extra.npc.config

// (c) https://github.com/FancyInnovations/FancyPlugins/blob/main/plugins/fancynpcs/fn-api/src/main/java/de/oliver/fancynpcs/api/data/property/NpcVisibility.java 
// MIT LICENSE

import com.google.common.collect.HashMultimap
import net.crystopia.crystalshard.extra.npc.INpc
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import java.util.*


enum class NpcVisibility(private val predicate: VisibilityPredicate) {
    /**
     * Everybody can see an NPC.
     */
    ALL(VisibilityPredicate { player: Player?, npc: INpc? -> true }),

    /**
     * The player needs permission to see a specific NPC.
     */
    PERMISSION_REQUIRED(
        VisibilityPredicate { player: Player?, npc: INpc? ->
            player!!.hasPermission(
                npc!!.permission ?: ""
            )
        }
    ),

    /**
     * The player needs to be added manually through the API
     */
    MANUAL(VisibilityPredicate { player: Player?, npc: INpc? ->
        ManualNpcVisibility.canSee(player!!,npc!!)
    });
    fun interface VisibilityPredicate {
        fun canSee(player: Player?, npc: INpc?): Boolean
    }

    /**
     * Handling of NpcVisibility.MANUAL
     */
    object ManualNpcVisibility {
        private val distantViewers: HashMultimap<NamespacedKey?, UUID?> = HashMultimap.create<NamespacedKey?, UUID?>()

        fun canSee(player: Player, npc: INpc): Boolean {
            return npc.isShownFor(player) || distantViewers.containsEntry(npc.id, player.uniqueId)
        }

        fun addDistantViewer(npc: INpc, uuid: UUID?) {
            distantViewers.put(npc.id, uuid)
        }

        fun addDistantViewer(npcName: String?, uuid: UUID?) {
            
        }

        fun removeDistantViewer(npc: INpc, uuid: UUID?) {
            distantViewers.remove(npc.id, uuid)
        }

        fun remove(npc: INpc) {
            distantViewers.removeAll(npc.id)
        }
        
        fun clear() {
            distantViewers.clear()
        }
    }
}