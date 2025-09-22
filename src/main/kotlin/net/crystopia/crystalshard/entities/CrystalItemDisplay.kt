package net.crystopia.crystalshard.entities

import org.bukkit.Material
import org.bukkit.entity.Display
import org.bukkit.entity.ItemDisplay
import org.bukkit.inventory.ItemStack

class CrystalItemDisplay(val handle: ItemDisplay) : CrystalEntity(handle = handle) {

    fun blockDisplay(block: CrystalItemDisplay.() -> Unit = {}) {
        CrystalItemDisplay(handle)
    }

    fun setMaterial(material: Material) {
        handle.setItemStack(ItemStack(material))
    }

    fun setBrightness(blockLight: Int, skyLight: Int) {
        handle.brightness = Display.Brightness(blockLight, skyLight)
    }
}