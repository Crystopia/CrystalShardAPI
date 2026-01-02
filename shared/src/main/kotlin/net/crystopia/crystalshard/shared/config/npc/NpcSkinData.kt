package net.crystopia.crystalshard.shared.config.npc

/**
 * Custom Skin Property Implementation for Minecraft Skins.
 * @see com.mojang.authlib.properties.Property
 */
class NpcSkinData {

    lateinit var texture: String
    lateinit var signature: String

    constructor(texture: String, signature: String) {
        this.texture = texture
        this.signature = signature
    }


}