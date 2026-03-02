package net.crystopia.crystalshard.paper.custom.world

import net.crystopia.crystalshard.paper.custom.world.data.WorldSettings
import org.bukkit.NamespacedKey
import org.bukkit.World
import org.bukkit.generator.BiomeProvider
import org.bukkit.generator.ChunkGenerator

fun customWorld(callback: CustomWorld.() -> Unit): CustomWorld {
    val customWorld = CustomWorld()
    callback.invoke(customWorld)
    return customWorld
}

class CustomWorld {

    private var key: NamespacedKey? = null
    private var generator: ChunkGenerator? = null
    private var biomeProvider: BiomeProvider? = null
    private var generatorSettings: String? = null
    private var settings: WorldSettings? = WorldSettings(
        seed = null,
        environment = null,
        type = null,
        generateStructures = null,
        hardcore = null,
        bonusChest = null
    )


    fun name(id: NamespacedKey): CustomWorld {
        this.key = id
        return this
    }

    fun settings(settings: WorldSettings): CustomWorld {
        this.settings = settings
        return this
    }

    fun <T : ChunkGenerator> generator(generator: T): CustomWorld {
        this.generator = generator
        return this
    }

    fun <T : BiomeProvider> biomeProvider(generator: T): CustomWorld {
        this.biomeProvider = generator
        return this
    }

    fun generatorSettings(settings: String): CustomWorld {
        this.generatorSettings = settings
        return this
    }

    fun seed(seed: Long): CustomWorld {
        this.settings?.seed = seed
        return this
    }

    fun delete(): CustomWorld {
        requireNotNull(key)
        WorldHelper.delete(this.key!!)
        return this
    }

    fun create(callback: World?.() -> Unit): World? {
        requireNotNull(key)
        requireNotNull(settings)

        return WorldHelper.create(
            key!!,
            settings!!,
            generator,
            biomeProvider,
            generatorSettings
        ) {
            callback.invoke(this)
        }
    }

}