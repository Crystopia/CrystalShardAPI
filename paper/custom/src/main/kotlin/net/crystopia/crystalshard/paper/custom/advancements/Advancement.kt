package net.crystopia.crystalshard.paper.custom.advancements

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent
import kotlinx.serialization.json.Json
import net.crystopia.crystalshard.common.extension.ifNull
import net.crystopia.crystalshard.paper.custom.advancements.models.AdvancementModel
import net.crystopia.crystalshard.paper.custom.smart.SmartEvents
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.advancement.AdvancementProgress
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import java.rmi.AlreadyBoundException

fun advancement(key: NamespacedKey,callback: Advancement.() -> Unit): Advancement {
    val advancement = Advancement(key)
    callback.invoke(advancement)
    return advancement
}

class Advancement {

    var advancementData: AdvancementModel? = null
    var key: NamespacedKey
    var advancement: org.bukkit.advancement.Advancement? = null

    constructor(key: NamespacedKey) {
        this.key = key
    }

    fun load(): Advancement {
        ifNull(advancementData) {
            throw NullPointerException("Advancement data cannot be null.")
        }
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
        SmartEvents.advancementCriterionGrantEvent[key] = action
        return this
    }

    fun doneEvent(action: PlayerAdvancementDoneEvent.() -> Unit = {}): Advancement {
        SmartEvents.playerAdvancementDoneEvent[key] = action
        return this
    }
}