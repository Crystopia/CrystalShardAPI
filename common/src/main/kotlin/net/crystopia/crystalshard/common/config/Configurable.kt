package net.crystopia.crystalshard.common.config

interface Configurable {
    fun save()
    fun load() {}
    fun reset() {}
}