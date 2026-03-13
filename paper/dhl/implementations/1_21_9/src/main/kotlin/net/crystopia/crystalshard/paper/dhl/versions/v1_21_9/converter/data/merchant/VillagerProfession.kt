package net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.converter.data.merchant

import com.google.common.base.Predicates
import com.google.common.collect.ImmutableSet
import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.VillagerProfession
import net.minecraft.core.Holder
import org.bukkit.craftbukkit.CraftSound
import org.bukkit.craftbukkit.block.CraftBlockType
import org.bukkit.craftbukkit.inventory.CraftItemStack

fun VillagerProfession.build(): net.minecraft.world.entity.npc.VillagerProfession {
    return net.minecraft.world.entity.npc.VillagerProfession(
        PaperAdventure.asVanilla(name),
        Predicates.`in`(mutableListOf(Holder.direct(heldJobSite.build()))),
        Predicates.`in`(mutableListOf(Holder.direct(acquirableJobSite.build()))),
        ImmutableSet.copyOf(requestedItems.map { stack -> CraftItemStack.asNMSCopy(stack).item }.toSet()),
        ImmutableSet.copyOf(blocks.map { block -> CraftBlockType.bukkitToMinecraft(block) }.toSet()),
        CraftSound.bukkitToMinecraft(workSound)
    )
}