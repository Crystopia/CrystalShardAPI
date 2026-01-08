package net.crystopia.crystalshard.paper.core.advancements

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.crystopia.crystalshard.paper.core.custom.CrystalEvents
import net.crystopia.crystalshard.paper.core.advancements.models.AdvancementModel
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.advancement.AdvancementProgress
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import java.rmi.AlreadyBoundException

class Advancement {

    var advancementData: net.crystopia.crystalshard.paper.core.advancements.models.AdvancementModel
    var key: NamespacedKey
    var advancement: org.bukkit.advancement.Advancement? = null

    constructor(key: NamespacedKey, advancementModel: net.crystopia.crystalshard.paper.core.advancements.models.AdvancementModel) {
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
        _root_ide_package_.net.crystopia.crystalshard.paper.core.custom.CrystalEvents.advancementCriterionGrantEvent[key] = action
        return this
    }

    fun doneEvent(action: PlayerAdvancementDoneEvent.() -> Unit = {}): Advancement {
        _root_ide_package_.net.crystopia.crystalshard.paper.core.custom.CrystalEvents.playerAdvancementDoneEvent[key] = action
        return this
    }
}