package net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.data.chunk

import net.crystopia.crystalshard.dhl.shared.data.chunk.LevelChunk
import net.minecraft.world.level.ChunkPos

fun LevelChunk.build(): net.minecraft.world.level.chunk.LevelChunk {
  return  net.minecraft.world.level.chunk.LevelChunk(
      world,
        ChunkPos(
            x,y
        )
    )
}