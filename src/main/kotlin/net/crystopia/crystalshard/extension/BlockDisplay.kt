package net.crystopia.crystalshard.extension

import org.bukkit.block.BlockType
import org.bukkit.entity.BlockDisplay
import org.bukkit.entity.Display


/**
 *
 * Better work with the Block Displays from Minecraft.
 *
 */


fun BlockDisplay.setBlock(blockType: BlockType) {
    val block = blockType.createBlockData()
    this.block = block
}

fun BlockDisplay.setBrightness(blockLight: Int, skyLight: Int) {
    brightness = Display.Brightness(blockLight, skyLight)
}

