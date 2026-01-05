package net.crystopia.crystalshard.extras.advancements

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.crystopia.crystalshard.extras.advancements.models.AdvancementModel
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey

class Advancement {

    var advancement: AdvancementModel
    var key: NamespacedKey

    constructor(key: NamespacedKey, advancementModel: AdvancementModel) {
        this.advancement = advancementModel
        this.key = key
    }

    fun load() {
        Bukkit.getUnsafe().loadAdvancement(key, Json.encodeToString(advancement))
    }

}