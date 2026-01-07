package net.crystopia.crystalshard.extras.advancements

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.crystopia.crystalshard.common.custom.CrystalEvents
import net.crystopia.crystalshard.extras.advancements.models.AdvancementModel
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.advancement.AdvancementProgress
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import java.rmi.AlreadyBoundException

class Advancement {

    var advancementData: AdvancementModel
    var key: NamespacedKey
    var advancement: org.bukkit.advancement.Advancement? = null

    constructor(key: NamespacedKey, advancementModel: AdvancementModel) {
        this.advancementData = advancementModel
        this.key = key
        this.advancement = Bukkit.getUnsafe().loadAdvancement(key, Json.encodeToString(advancementData))
    }

    fun load(): Advancement {
        try {
            advancement = Bukkit.getUnsafe().loadAdvancement(key, Json.encodeToString(advancementData))
        } catch (e: Exception) {
            throw AlreadyBoundException("Advancement is autoloaded when created...")
        }
        return this
    }

    fun complete(player: Player, callback: AdvancementProgress.() -> Unit): Advancement {
        val process = player.getAdvancementProgress(advancement!!)
        advancement!!.criteria.forEach { text -> process.awardCriteria(text) }
        callback(process)
        return this
    }

    fun completeCriteria(player: Player, criteria: String, callback: AdvancementProgress.() -> Unit): Advancement {
        val process = player.getAdvancementProgress(advancement!!)
        process.awardCriteria(criteria)
        callback(process)
        return this
    }

    fun progress(player: Player, callback: AdvancementProgress.() -> Unit): Advancement {
        callback(player.getAdvancementProgress(advancement!!))
        return this
    }

    fun remove(): Advancement {
        Bukkit.getUnsafe().removeAdvancement(key)
        return this
    }

    fun criterionGrantEvent(action: PlayerAdvancementCriterionGrantEvent.() -> Unit = {}): Advancement {
        CrystalEvents.advancementCriterionGrantEvent[key] = action
        return this
    }

    fun doneEvent(action: PlayerAdvancementDoneEvent.() -> Unit = {}): Advancement {
        CrystalEvents.playerAdvancementDoneEvent[key] = action
        return this
    }
}