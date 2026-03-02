package net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons

import net.kyori.adventure.text.event.ClickEvent
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceLocation
import org.bukkit.NamespacedKey
import java.net.URI

abstract class Action<T : Any>

data class ActionCustomAll(
    var id: NamespacedKey,
    var additionsNBT: MutableMap<String, String>
) : Action<ActionCustomAll>()

data class ActionStaticAction(
    var event: ClickEvent
) : Action<ActionStaticAction>()
