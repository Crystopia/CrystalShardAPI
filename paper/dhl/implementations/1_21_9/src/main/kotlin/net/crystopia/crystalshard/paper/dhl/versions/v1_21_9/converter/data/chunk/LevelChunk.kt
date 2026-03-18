package net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.converter.data.chunk

import net.crystopia.crystalshard.paper.dhl.shared.data.chunk.LevelChunk
import net.minecraft.world.level.ChunkPos
import org.bukkit.craftbukkit.CraftWorld

fun LevelChunk.build(): net.minecraft.world.level.chunk.LevelChunk {
  return  net.minecraft.world.level.chunk.LevelChunk(
        (world as CraftWorld).handle,
        ChunkPos(
            x,y
        )
    )
}