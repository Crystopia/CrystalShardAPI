package net.crystopia.crystalshard.paper.core.extension

import org.bukkit.Material
import org.bukkit.entity.Display
import org.bukkit.entity.ItemDisplay
import org.bukkit.inventory.ItemStack


/**
 *
 * Better work with the Item Displays from Minecraft.
 *
 */

fun ItemDisplay.setMaterial(material: Material) {
    this.setItemStack(ItemStack(material))
}

fun ItemDisplay.setBrightness(blockLight: Int, skyLight: Int) {
    this.brightness = Display.Brightness(blockLight, skyLight)
}
