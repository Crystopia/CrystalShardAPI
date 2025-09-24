package net.crystopia.crystalshard.config

interface Configurable {
    fun save()
    fun load() {}
    fun reset() {}
}