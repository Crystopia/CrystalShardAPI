package net.crystopia.crystalshard.paper.custom.world

import net.crystopia.crystalshard.paper.custom.world.data.WorldSettings
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.generator.BiomeProvider
import org.bukkit.generator.ChunkGenerator
import kotlin.io.path.deleteIfExists

object WorldHelper {

    fun create(
        key: NamespacedKey,
        settings: WorldSettings,
        generator: ChunkGenerator? = null,
        biomeProvider: BiomeProvider? = null,
        generatorSettings: String? = null,
        callback: World?.() -> Unit
    ): World? {

        val creator = WorldCreator(
            key
        )
        if (settings.seed != null) creator.seed(settings.seed!!)
        if (settings.type != null) creator.type(settings.type!!)
        if (settings.environment != null) creator.environment(settings.environment!!)
        if (generator != null) creator.generator(generator)
        if (biomeProvider != null) creator.biomeProvider(biomeProvider)
        if (generatorSettings != null) creator.generatorSettings(generatorSettings)
        if (settings.generateStructures != null) creator.generateStructures(settings.generateStructures!!)
        if (settings.bonusChest != null) creator.bonusChest(settings.bonusChest!!)
        if (settings.hardcore != null) creator.hardcore(settings.hardcore!!)

        val world = Bukkit.createWorld(creator)
        callback.invoke(world)
        return world
    }

    fun delete(key: NamespacedKey) {
        Bukkit.getWorld(key)?.worldFolder?.delete()
        Bukkit.getWorld(key)?.worldPath?.deleteIfExists()
    }

}